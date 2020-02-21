package domain.statements.exam.lock;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.exceptions.GenericException;
import domain.exceptions.statementExecutionExceptions.UnmatchedTypesExpression;
import domain.exceptions.statementExecutionExceptions.VariableNotDeclaredException;
import domain.exceptions.statementExecutionExceptions.VariableNotInLatchTableException;
import domain.statements.IStatement;
import domain.types.IntegerType;
import domain.types.Type;
import domain.values.IntegerValue;
import domain.values.Value;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStatement implements IStatement {
    private String var;
    private static Lock lock = new ReentrantLock();

    public LockStatement(String var){
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        lock.lock();
        if(state.getSymbolTable().containsKey(this.var)){
            Value varValue = state.getSymbolTable().get(var);
            if(varValue.getType().equals(new IntegerType())){
                Integer intVal = ((IntegerValue)varValue).getValue();
                if (state.getLockTable().containsKey(intVal)) {
                    Integer lockValue = state.getLockTable().get(intVal);
                    if(lockValue == -1){
                        state.getLockTable().put(intVal, state.getID());
                    }else{
                        state.getExecutionStack().push(this);
                    }
                }else{
                    lock.unlock();
                    throw new VariableNotInLatchTableException("Index " + intVal + " not in latchTable");
                }
            }else{
                lock.unlock();
                throw new UnmatchedTypesExpression("Var " + var + " is not of type int");
            }
        }else{
            lock.unlock();
            throw new VariableNotDeclaredException("Variable " + var +" is not declared");
        }
        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        if(typeEnv.get(var).equals(new IntegerType())){
            return typeEnv;
        }else{
            throw new UnmatchedTypesExpression(var + " is not of type Integer!");
        }
    }

    @Override
    public String toString(){
        return "lock( " + var + " )";
    }
}

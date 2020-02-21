package domain.statements.exam.latch;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.exceptions.GenericException;
import domain.exceptions.statementExecutionExceptions.UnmatchedTypesExpression;
import domain.exceptions.statementExecutionExceptions.VariableNotDeclaredException;
import domain.exceptions.statementExecutionExceptions.VariableNotInLatchTableException;
import domain.expressions.ValueExpression;
import domain.statements.IStatement;
import domain.statements.PrintStatement;
import domain.types.IntegerType;
import domain.types.Type;
import domain.values.IntegerValue;
import domain.values.Value;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownStatement implements IStatement {
    private String var;
    private static Lock lock = new ReentrantLock();

    public CountDownStatement(String var){
        this.var = var;
    }


    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        lock.lock();
        if(state.getSymbolTable().containsKey(var)){
            Value varValue = state.getSymbolTable().get(var);
            if(varValue.getType().equals(new IntegerType())){
                Integer intVal = ((IntegerValue)varValue).getValue();
                if(state.getLatchTable().containsKey(intVal)){
                    Integer latchValue = state.getLatchTable().get(intVal);
                    if(latchValue > 0){
                        state.getLatchTable().put(intVal, latchValue - 1);
                        state.getExecutionStack().push(new PrintStatement(new ValueExpression(new IntegerValue(state.getID()))));
                    }
                }else{
                    lock.unlock();
                    throw new VariableNotInLatchTableException(intVal + " not in Latch Table");
                }
            }else{
                lock.unlock();
                throw new UnmatchedTypesExpression(var + " is not of type int");
            }
        }else{
            lock.unlock();
            throw new VariableNotDeclaredException(var + " is not declared");
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
        }    }

    @Override
    public String toString(){
        return "countDown( " + var + " )";
    }
}

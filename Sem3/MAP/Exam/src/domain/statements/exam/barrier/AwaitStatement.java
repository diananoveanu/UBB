package domain.statements.exam.barrier;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.adt.utils.Pair;
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

public class AwaitStatement implements IStatement {
    private String var;
    private static Lock lock = new ReentrantLock();

    public AwaitStatement(String var){
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        lock.lock();
        if (state.getSymbolTable().containsKey(var)) {
            Value varValue = state.getSymbolTable().get(var);
            if (varValue.getType().equals(new IntegerType())) {
                Integer intVal = ((IntegerValue) varValue).getValue();
                if (state.getBarrierTable().containsKey(intVal)) {
                    if (state.getBarrierTable().get(intVal).getInteger() > state.getBarrierTable().get(intVal).getIntegerList().size()) {
                        if (state.getBarrierTable().get(intVal).getIntegerList().contains(state.getID())) {
                            state.getExecutionStack().push(this);
                        } else {
                            state.getBarrierTable().get(intVal).getIntegerList().add(state.getID());
                            state.getExecutionStack().push(this);
                        }
                    }
                } else {
                    lock.unlock();
                    throw new VariableNotInLatchTableException(var + " not in barrier table");
                }
            } else {
                lock.unlock();
                throw new UnmatchedTypesExpression(var + " is not of type int");
            }
        } else {
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
        }
    }

    @Override
    public String toString() {
        return "awaitBarrier( " + var + " )";
    }
}

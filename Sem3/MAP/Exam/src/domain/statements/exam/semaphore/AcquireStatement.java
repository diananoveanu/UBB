package domain.statements.exam.semaphore;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.adt.utils.Pair;
import domain.exceptions.GenericException;
import domain.exceptions.repoExceptions.IOException;
import domain.exceptions.statementExecutionExceptions.SemaphoreLocationNotFoundException;
import domain.exceptions.statementExecutionExceptions.UnmatchedTypesExpression;
import domain.exceptions.statementExecutionExceptions.VariableNotDeclaredException;
import domain.statements.IStatement;
import domain.types.IntegerType;
import domain.types.Type;
import domain.values.IntegerValue;
import domain.values.Value;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AcquireStatement implements IStatement {
    private String var;
    private static Lock lock = new ReentrantLock();
    public AcquireStatement(String var){
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException, IOException {
        lock.lock();
        if(state.getSymbolTable().containsKey(var)){
            Value foundIndex = state.getSymbolTable().get(var);
            if(foundIndex.getType().equals(new IntegerType())){
                if(state.getSemaphoreTable().exists(((IntegerValue)foundIndex).getValue())){
                    Pair semaphorePair = state.getSemaphoreTable().getSemaphore().get(((IntegerValue)foundIndex).getValue());
                    if(semaphorePair.getInteger() > semaphorePair.getIntegerList().size()){
                        if(!semaphorePair.getIntegerList().contains(state.getID())){
                            semaphorePair.getIntegerList().add(state.getID());
                        }
                    }else{
                        state.getExecutionStack().push(this);
                    }
                }else{
                    lock.unlock();
                    throw new SemaphoreLocationNotFoundException(foundIndex.toString() + " is not in semaphore");
                }
            }else{
                lock.unlock();
                throw new UnmatchedTypesExpression(var + " type is not Integer");
            }
        }else{
            lock.unlock();
            throw new  VariableNotDeclaredException("Variable " + var + " is not declared");
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
        return "acquire(" + var + ")";
    }

}

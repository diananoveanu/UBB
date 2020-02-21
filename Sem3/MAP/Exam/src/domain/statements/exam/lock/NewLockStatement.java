package domain.statements.exam.lock;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.exceptions.GenericException;
import domain.exceptions.statementExecutionExceptions.UnmatchedTypesExpression;
import domain.statements.IStatement;
import domain.types.IntegerType;
import domain.types.Type;
import domain.values.IntegerValue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLockStatement implements IStatement {
    private String var;
    private static Lock lock = new ReentrantLock();

    public NewLockStatement(String v){
        this.var = v;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        lock.lock();
        Integer location = state.getFreeLockAddress();
        state.getLockTable().put(location, -1);
        state.getSymbolTable().put(var, new IntegerValue(location));
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
        return "newLock( " + var + " )";
    }
}

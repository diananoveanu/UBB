package domain.statements.exam.latch;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.exceptions.GenericException;
import domain.exceptions.statementExecutionExceptions.UnmatchedTypesExpression;
import domain.expressions.Expression;
import domain.statements.IStatement;
import domain.statements.exam.barrier.NewBarrierStatement;
import domain.types.IntegerType;
import domain.types.Type;
import domain.values.IntegerValue;
import domain.values.Value;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLatchStatement implements IStatement {
    private String var;
    private Expression expression;
    private static Lock lock = new ReentrantLock();

    public NewLatchStatement(String var, Expression exp){
        this.var = var;
        this.expression = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        lock.lock();
        Value expValue = expression.evaluate(state.getSymbolTable(), state.getHeapTable());
        if(expValue.getType().equals(new IntegerType())){
            Integer latchAddress = state.getFreeLatchAddress();
            state.getLatchTable().put(latchAddress, ((IntegerValue)expValue).getValue());
            state.getSymbolTable().put(var, new IntegerValue(latchAddress));
        }else{
            lock.unlock();
            throw new UnmatchedTypesExpression(expression.toString() + " does not evaluates to int");
        }
        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        if(typeEnv.get(var).equals(new IntegerType())){
            if(expression.typeCheck(typeEnv).equals(new IntegerType())){
                return typeEnv;
            }else{
                throw new UnmatchedTypesExpression(expression.toString() + " is not of type int");
            }
        }else{
            throw new UnmatchedTypesExpression(var + " is not of type Integer!");
        }
    }

    @Override
    public String toString(){
        return "newLatch( " + var + ", " + expression.toString() + " )";
    }
}

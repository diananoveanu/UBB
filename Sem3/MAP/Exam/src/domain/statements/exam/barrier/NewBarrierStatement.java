package domain.statements.exam.barrier;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.adt.utils.Pair;
import domain.exceptions.GenericException;
import domain.exceptions.statementExecutionExceptions.UnmatchedTypesExpression;
import domain.expressions.Expression;
import domain.statements.IStatement;
import domain.types.IntegerType;
import domain.types.Type;
import domain.values.IntegerValue;
import domain.values.Value;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewBarrierStatement implements IStatement {
    private String var;
    private Expression expression;
    private static Lock lock = new ReentrantLock();

    public NewBarrierStatement(String var, Expression exp){
        this.var = var;
        this.expression = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        lock.lock();
        Value exprValue = expression.evaluate(state.getSymbolTable(), state.getHeapTable());
        if(exprValue.getType().equals(new IntegerType())){
            Integer value =  ((IntegerValue)exprValue).getValue();
            Integer location = state.getFreeBarrierAddress();
            state.getBarrierTable().put(location, new Pair(value, new ArrayList<>()));
            state.getSymbolTable().put(var, new IntegerValue(location));
        }else{
            lock.unlock();
            throw new UnmatchedTypesExpression(expression.toString() + " does not evaluate to int");
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
                throw new UnmatchedTypesExpression(expression.toString() +" is not of type int");
            }
        }else{
            throw new UnmatchedTypesExpression(var + " is not of type Integer!");
        }
    }

    @Override
    public String toString(){
        return "newBarrier( " + var + ", " + expression.toString() + " )";
    }
}

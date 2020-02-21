package domain.statements.exam.semaphore;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.adt.utils.Pair;
import domain.exceptions.GenericException;
import domain.exceptions.statementExecutionExceptions.UnmatchedTypesExpression;
import domain.exceptions.statementExecutionExceptions.VariableNotDeclaredException;
import domain.expressions.Expression;
import domain.statements.IStatement;
import domain.types.IntegerType;
import domain.types.Type;
import domain.values.IntegerValue;
import domain.values.Value;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CreateSemaphoreStatement implements IStatement {
    private String var;
    private Expression expression;
    private static Lock lock = new ReentrantLock();
    public CreateSemaphoreStatement(String var, Expression expression){
        this.var = var;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        lock.lock();
        Value number1 = expression.evaluate(state.getSymbolTable(), state.getHeapTable());
        if(number1.getType().equals(new IntegerType())){
            int location = state.getSemaphoreTable().getSemaphoreAddress();
            state.getSemaphoreTable().put(location, new Pair(((IntegerValue)number1).getValue(), new ArrayList<>()));
            if(state.getSymbolTable().containsKey(var) && state.getSymbolTable().get(var).getType().equals(new IntegerType())){
                state.getSymbolTable().put(var, new IntegerValue(location));
            }else{
                lock.unlock();
                throw new VariableNotDeclaredException("Variable " + var + " does not exist!");
            }
        }else{
            lock.unlock();
            throw new UnmatchedTypesExpression("Semaphore expression is not Integer, instead is " + number1.getType().toString());
        }
        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        if(typeEnv.get(var).equals(new IntegerType()) && expression.typeCheck(typeEnv).equals(new IntegerType())){
            return typeEnv;
        }else{
            throw new UnmatchedTypesExpression(var + " or " + expression.toString() + " is not of type Integer");
        }
    }

    @Override
    public String toString(){
        return "createSemaphore(" + var + ", " + expression.toString() + ")";
    }
}

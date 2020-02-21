package domain.statements.exam.toySemaphore;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.adt.utils.Tuple;
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

public class CreateToySemaphoreStatement implements IStatement {
    private String var;
    private Expression expression1;
    private Expression expression2;
    private static Lock lock = new ReentrantLock();
    public CreateToySemaphoreStatement(String var, Expression expression1, Expression expression2){
        this.var = var;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        lock.lock();
        Value number1 = expression1.evaluate(state.getSymbolTable(), state.getHeapTable());
        Value number2 = expression2.evaluate(state.getSymbolTable(), state.getHeapTable());
        if(number1.getType().equals(new IntegerType())){
            if(number2.getType().equals(new IntegerType())) {
                int location = state.getToySemaphoreTable().getSemaphoreAddress();
                state.getToySemaphoreTable().put(location, new Tuple(((IntegerValue) number1).getValue(), new ArrayList<>(), ((IntegerValue)number2).getValue()));
                if (state.getSymbolTable().containsKey(var) && state.getSymbolTable().get(var).getType().equals(new IntegerType())) {
                    state.getSymbolTable().put(var, new IntegerValue(location));
                } else {
                    lock.unlock();
                    throw new VariableNotDeclaredException("Variable " + var + " does not exist!");
                }
            }else{
                lock.unlock();
                throw new UnmatchedTypesExpression("Semaphore's second expression is not Integer, instead is " + number2.getType().toString());
            }
        }else{
            lock.unlock();
            throw new UnmatchedTypesExpression("Semaphore's first expression is not Integer, instead is " + number1.getType().toString());
        }
        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        if(typeEnv.get(var).equals(new IntegerType()) && expression1.typeCheck(typeEnv).equals(new IntegerType())
                            && expression2.typeCheck(typeEnv).equals(new IntegerType())){
            return typeEnv;
        }else{
            throw new UnmatchedTypesExpression(var + " or " + expression1.toString() + " is not of type Integer");
        }
    }

    @Override
    public String toString(){
        return "createToySemaphore(" + var + ", " + expression1.toString() + ")";
    }
}

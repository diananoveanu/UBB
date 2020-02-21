package domain.statements.exam;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.exceptions.GenericException;
import domain.exceptions.statementExecutionExceptions.UnmatchedTypesExpression;
import domain.expressions.Expression;
import domain.expressions.ValueExpression;
import domain.statements.IStatement;
import domain.types.BooleanType;
import domain.types.IntegerType;
import domain.types.Type;
import domain.values.IntegerValue;

public class SleepStatement implements IStatement {
    //- pop the statement
    //- if number== 0 then do nothing
    //else push sleep(number-1) on the stack

    Expression number;

    public SleepStatement(Expression number) {
        this.number = number;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        if (number.evaluate(state.getSymbolTable(), state.getHeapTable()).getType().equals(new IntegerType())) {
            if (!((IntegerValue) number.evaluate(state.getSymbolTable(), state.getHeapTable())).getValue().equals(0)) {
                state.getExecutionStack().push(new SleepStatement(new ValueExpression(new IntegerValue(((IntegerValue) number.evaluate(state.getSymbolTable(), state.getHeapTable())).getValue() - 1))));
            }
        } else
            throw new UnmatchedTypesExpression("Type of parameter number" + number.toString() + " is invalid in sleep expression");
        return null;
    }

    @Override
    public String toString() {
        return "SleepStatement{" +
                "number=" + number +
                '}';
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        Type typeExp = number.typeCheck(typeEnv);
        if (typeExp.equals(new IntegerType())) {
            return typeEnv;
        } else throw new UnmatchedTypesExpression("The parameter of SLEEP doesn't have the type integer");
    }
}

package domain.statements.exam;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.exceptions.GenericException;
import domain.exceptions.statementExecutionExceptions.UnmatchedTypesExpression;
import domain.expressions.Expression;
import domain.statements.IStatement;
import domain.types.BooleanType;
import domain.types.Type;
import domain.values.BooleanValue;

public class RepeatUntilStatement implements IStatement {
    Expression e;
    IStatement s;

    public RepeatUntilStatement(Expression e, IStatement s) {
        this.e = e;
        this.s = s;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        if (e.evaluate(state.getSymbolTable(), state.getHeapTable()).getType().equals(new BooleanType())) {
            if (e.evaluate(state.getSymbolTable(), state.getHeapTable()).equals(new BooleanValue(false))) {
                state.getExecutionStack().push(new RepeatUntilStatement(e, s));
                state.getExecutionStack().push(s);
            }
        } else throw new UnmatchedTypesExpression("Expresion is not bool!");
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        Type typeExp = e.typeCheck(typeEnv);
        if (typeExp.equals(new BooleanType())) {
            s.typeCheck(typeEnv.cloneDict());
            return typeEnv;
        } else
            throw new GenericException("The condition of REPEAT .. UNTIL doesn't have the type bool");
    }
}


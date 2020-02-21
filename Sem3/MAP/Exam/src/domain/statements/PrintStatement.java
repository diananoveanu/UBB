package domain.statements;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;
import domain.exceptions.GenericException;
import domain.expressions.Expression;
import domain.types.Type;
import domain.values.Value;

public class PrintStatement implements IStatement {
    Expression expression;

    public PrintStatement(Expression e) {
        this.expression = e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        Value value = expression.evaluate(state.getSymbolTable(), state.getHeapTable());
        state.getOut().add(value);
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }
}

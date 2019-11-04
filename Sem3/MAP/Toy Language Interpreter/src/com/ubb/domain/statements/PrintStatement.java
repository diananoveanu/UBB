package com.ubb.domain.statements;

import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.expressions.Expression;

public class PrintStatement implements IStatement {
    private Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return ("print( " + expression.toString() + " )");
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        try {
            state.addOut(expression.evaluate(state.getSymTable()));
        } catch (GenericException e) {
            throw new GenericException(e.getMessage());
        }
        return state;
    }
}

package com.ubb.domain.statements;

import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.expressions.Expression;

public class IfStatement implements IStatement {
    Expression expression;
    IStatement thenS;
    IStatement elseS;

    IfStatement(Expression e, IStatement t, IStatement el) {
        expression = e;
        thenS = t;
        elseS = el;
    }

    public String toString() {
        return "(IF(" + expression.toString() + ") THEN(" + thenS.toString()
                + ")ELSE(" + elseS.toString() + "))";
    }

    public ProgramState execute(ProgramState state) throws GenericException {
        //TODO
        return state;
    }

}

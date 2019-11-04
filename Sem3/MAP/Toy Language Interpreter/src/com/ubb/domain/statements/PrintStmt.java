package com.ubb.domain.statements;

import com.ubb.domain.PrgState;
import com.ubb.domain.expressions.Exp;

public class PrintStmt implements IStmt {
    Exp exp;

    @Override
    public String toString() {
        return ("print(" + exp.toString() + ")");
    }

    @Override
    public PrgState execute(PrgState state) {
        return state;
    }
}

package com.ubb.domain.statements;

import com.ubb.domain.PrgState;

public class NopStmt implements IStmt {
    @Override
    public String toString() {
        return "";
    }

    @Override
    public PrgState execute(PrgState state) {
        return state;
    }
}

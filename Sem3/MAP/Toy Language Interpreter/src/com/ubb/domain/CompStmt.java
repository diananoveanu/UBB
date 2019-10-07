package com.ubb.domain;

import com.ubb.adt.MyIStack;

public class CompStmt implements IStmt {

    IStmt first;
    IStmt second;

    @Override
    public String toString() {
        return "(" + first.toString() + ";" + second.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack<IStmt> stk = state.getStk();
        stk.push(second);
        stk.push(first);
        return state;
    }

}

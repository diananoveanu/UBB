package com.ubb.controller;

import com.ubb.adt.stack.MyIStack;
import com.ubb.domain.PrgState;
import com.ubb.domain.statements.IStmt;

public class Controller {
    private Repos
    PrgState oneStep(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        if (stk.isEmpty()) throws new MyException("prgstate stack is empty");
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }
    //TODO
}

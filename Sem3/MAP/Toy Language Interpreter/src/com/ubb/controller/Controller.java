package com.ubb.controller;

import com.ubb.adt.list.MyIList;
import com.ubb.adt.stack.MyIStack;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.statements.IStatement;
import com.ubb.repository.Repository;

public class Controller {
    private Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }

    ProgramState oneStep(ProgramState state) throws GenericException {
        MyIStack<IStatement> stk = state.getExeStack();
        if (stk.isEmpty()) throws new GenericException("prgstate stack is empty");
        IStatement crtStmt = stk.pop();
        return crtStmt.execute(state);
        //TODO

    }

    public void allSteps() throws GenericException {
        //TODO
    }


    void displayProgramState() {
        //TODO
    }
}

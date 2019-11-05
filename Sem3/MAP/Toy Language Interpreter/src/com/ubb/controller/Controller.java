package com.ubb.controller;

import com.ubb.adt.list.MyIList;
import com.ubb.adt.stack.MyIStack;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.statements.IStatement;
import com.ubb.repository.IRepository;
import com.ubb.repository.Repository;

public class Controller {
    private IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    ProgramState oneStep(ProgramState state) throws GenericException {
        MyIStack<IStatement> stk = state.getExeStack();
        if (stk.isEmpty()) throw new GenericException("Program state stack is empty");
        IStatement crtStmt = stk.pop();

        return crtStmt.execute(state);
    }

    public void allStep() throws GenericException {
        ProgramState prg = repository.getCurrentProgram();
        // repo is the controller field of type MyRepoInterface
        //here you can display the prg state
        while (!prg.getExeStack().isEmpty()) {
            oneStep(prg);
            try{
                this.repository.logPrgStateExec(prg);
            }
            catch (Exception e){
                e.printStackTrace();
                // todo lol
            }
        }
        //here you can display the pr g state }
    }


    void displayProgramState() {
        //TODO
    }
}

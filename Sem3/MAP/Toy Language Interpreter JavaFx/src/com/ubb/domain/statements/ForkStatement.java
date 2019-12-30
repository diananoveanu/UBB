package com.ubb.domain.statements;

import com.ubb.adt.stack.MyStack;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;

public class ForkStatement implements IStatement {
    private IStatement paramStmt;

    public ForkStatement(IStatement paramStmt){
        this.paramStmt = paramStmt;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        return new ProgramState(new MyStack<>(), state.getSymTable().cloneDict(),
                state.getOut(), state.getFileTable(), state.getHeap(), this.paramStmt, state.getId() * 10);
    }

    @Override
    public String toString(){
        return "fork( " + paramStmt.toString() + " )";
    }
}

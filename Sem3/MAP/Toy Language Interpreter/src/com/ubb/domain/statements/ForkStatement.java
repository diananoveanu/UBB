package com.ubb.domain.statements;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.adt.stack.MyStack;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.type.Type;

public class ForkStatement implements IStatement {
    private IStatement paramStmt;

    public ForkStatement(IStatement paramStmt) {
        this.paramStmt = paramStmt;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        return new ProgramState(new MyStack<>(), state.getSymTable().cloneDict(),
                state.getOut(), state.getFileTable(), state.getHeap(), this.paramStmt, state.getId() * 10);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws GenericException {
        return paramStmt.typeCheck(typeEnv);
    }

    @Override
    public String toString() {
        return "fork( " + paramStmt.toString() + " )";
    }
}

package com.ubb.domain.statements;

import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;

public class NopStatement implements IStatement {
    @Override
    public String toString() {
        return "";
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        //return state;
        return null;
    }
}

package com.ubb.domain.statements;

import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.type.Type;

public class VariableDeclarationStatement implements IStatement {
    String name;
    Type type; //TODO

    @Override
    public String toString() {
        return "";
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        //TODO
        return state;
    }
}

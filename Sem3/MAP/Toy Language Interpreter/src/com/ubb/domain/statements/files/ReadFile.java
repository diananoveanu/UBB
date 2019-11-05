package com.ubb.domain.statements.files;

import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.expressions.Expression;
import com.ubb.domain.statements.IStatement;

public class ReadFile implements IStatement {
    private Expression path;


    // todo
    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        return null;
    }
    //still in implementation
}

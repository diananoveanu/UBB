package com.ubb.domain.statements;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.type.Type;

public class NopStatement implements IStatement {
    @Override
    public String toString() {
        return "";
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws GenericException {
        return typeEnv;
    }
}

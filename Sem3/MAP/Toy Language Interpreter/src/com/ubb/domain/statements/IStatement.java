package com.ubb.domain.statements;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.type.Type;

public interface IStatement {
    ProgramState execute(ProgramState state) throws GenericException;

    MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws
            GenericException;
}

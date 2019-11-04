package com.ubb.domain.statements;

import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;

public interface IStatement {
    ProgramState execute(ProgramState state) throws GenericException;
}

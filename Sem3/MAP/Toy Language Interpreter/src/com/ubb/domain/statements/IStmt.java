package com.ubb.domain.statements;

import com.ubb.domain.PrgState;

public interface IStmt {
    PrgState execute(PrgState state);
}

package com.ubb.domain.statements;

import com.ubb.domain.PrgState;

public class VarDeclStmt implements IStmt {
    String name;
    Type typ; //TODO

    @Override
    public String toString() {
        return "";
    }

    @Override
    public PrgState execute(PrgState state){
        //TODO
        return state;
    }
}

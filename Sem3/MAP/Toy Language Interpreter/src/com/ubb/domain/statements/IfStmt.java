package com.ubb.domain.statements;

import com.ubb.domain.PrgState;
import com.ubb.domain.expressions.Exp;

public class IfStmt implements IStmt {
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    IfStmt(Exp e, IStmt t, IStmt el) {
        exp = e;
        thenS = t;
        elseS = el;
    }

    public String toString() {
        return "(IF(" + exp.toString() + ") THEN(" + thenS.toString()
                + ")ELSE(" + elseS.toString() + "))";
    }

    public PrgState execute(PrgState state) {
        //TODO
        return state;
    }

}

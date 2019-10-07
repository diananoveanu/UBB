package com.ubb.domain.statements;

import com.ubb.adt.dict.MyIDictionary;
import com.ubb.adt.stack.MyIStack;
import com.ubb.domain.PrgState;
import com.ubb.domain.expressions.Exp;

public class AssignStmt implements IStmt {
    String id;
    Exp exp;

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack<IStmt> stack = state.getExeStack();
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeap();
        int value = 0;
        value = exp.eval(symbolTable, heapTable);
        symbolTable.put(name, value);
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }
}

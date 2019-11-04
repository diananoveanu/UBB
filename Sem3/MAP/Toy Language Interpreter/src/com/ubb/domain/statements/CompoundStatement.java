package com.ubb.domain.statements;

import com.ubb.adt.stack.MyIStack;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;

public class CompoundStatement implements IStatement {
    private IStatement first;
    private IStatement second;
    private IStatement third;

    public CompoundStatement(IStatement first, IStatement second, IStatement third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public String toString() {
        return "(" + first.toString() + ";" + second.toString() +";" + third.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        MyIStack<IStatement> stk = state.getExeStack();
        stk.push(third);
        stk.push(second);
        stk.push(first);
        return state;
    }
}

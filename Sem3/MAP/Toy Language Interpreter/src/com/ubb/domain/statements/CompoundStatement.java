package com.ubb.domain.statements;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.adt.stack.MyIStack;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.type.Type;

public class CompoundStatement implements IStatement {
    private IStatement first;
    private IStatement second;

    public CompoundStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return first.toString() + ";\n" + second.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        MyIStack<IStatement> stk = state.getExeStack();
        stk.push(second);
        stk.push(first);

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws GenericException {
        return second.typeCheck(first.typeCheck(typeEnv));
    }
}

package com.ubb.domain.statements;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.adt.stack.MyIStack;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.expressions.Expression;
import com.ubb.domain.type.BooleanType;
import com.ubb.domain.type.Type;
import com.ubb.domain.value.BooleanValue;
import com.ubb.domain.value.Value;

public class IfStatement implements IStatement {
    private Expression expression;
    private IStatement thenS;
    private IStatement elseS;

    public IfStatement(Expression e, IStatement t, IStatement el) {
        expression = e;
        thenS = t;
        elseS = el;
    }

    public String toString() {
        return "if(" + expression.toString() + ") then\t\n\t(" + thenS.toString()
                + ");\nelse\n\t(" + elseS.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        MyIStack<IStatement> stack = state.getExeStack();
        Value value;
        value = expression.evaluate(state.getSymTable(), state.getHeap());
        if (value.getType().equals(new BooleanType())) {
            BooleanValue val = (BooleanValue) value;
            boolean result = val.getValue();
            if (result) {
                stack.push(thenS);
            } else {
                stack.push(elseS);
            }
        } else {
            throw new GenericException("The expression is not a boolean!");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws GenericException {
        Type typeExp = expression.typeCheck(typeEnv);
        if (typeExp.equals(new BooleanType())) {
            thenS.typeCheck(typeEnv.cloneDict());
            elseS.typeCheck(typeEnv.cloneDict());
            return typeEnv;
        } else
            throw new GenericException("The condition of IF doesn't have the type bool");
    }
}


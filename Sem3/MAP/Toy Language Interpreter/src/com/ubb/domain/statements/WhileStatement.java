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

public class WhileStatement implements IStatement {
    private IStatement statement;
    private Expression expr;

    public WhileStatement(Expression expr, IStatement stmt) {
        this.statement = stmt;
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "while( " + expr.toString() + " ) { " + statement.toString() + " }";
    }


    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<Integer, Value> heapTable = state.getHeap();

        Value exprValue = expr.evaluate(symTable, heapTable);
        if (exprValue.getType().equals(new BooleanType())) {
            if (exprValue.equals(new BooleanValue(true))) {
                MyIStack<IStatement> stack = state.getExeStack();
                stack.push(this);
                state.setExeStack(stack);
                statement.execute(state);
            }
        } else {
            throw new GenericException("Expresion is not bool!");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws GenericException {
        Type typeExp = expr.typeCheck(typeEnv);
        if (typeExp.equals(new BooleanType())) {
            statement.typeCheck(typeEnv.cloneDict());
            return typeEnv;
        } else
            throw new GenericException("The condition of WHILE doesn't have the type bool");
    }
}

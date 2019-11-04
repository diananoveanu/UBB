package com.ubb.domain.statements;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.adt.stack.MyIStack;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.expressions.Expression;
import com.ubb.domain.type.Type;
import com.ubb.domain.value.Value;

//TODO finish this shit

public class AssignStatement implements IStatement {
    private String id;
    private Expression expression;

    @Override
    public String toString() {
        return id + "=" + expression.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        MyIStack<IStatement> executionStack = state.getExeStack();
        MyIDictionary<String, Value> symbolTable = state.getSymTable();
        if (symbolTable.isDefined(id)) {
            Value value = expression.evaluate(symbolTable);
            Type typeId = (symbolTable.lookup(id)).getType();
            if ((value.getType()).equals(typeId)) {
                symbolTable.put(id, value);
            } else {
                throw new GenericException("declared type of variable: " + id + "and type of expression assigned don't match");
            }
        } else {
            throw new GenericException("the used variable: " + id + " was not declared before");
        }
        return state;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Expression getExpression() {
        return this.expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}


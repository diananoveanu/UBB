package com.ubb.domain.expressions;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.type.Type;
import com.ubb.domain.value.Value;


public class VariableExpression implements Expression {
    private String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symbolTable, MyIDictionary<Integer, Value> heap) throws GenericException {
        return symbolTable.get(this.name);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws GenericException {
        return typeEnv.get(name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}

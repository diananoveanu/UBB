package com.ubb.domain.expressions;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.value.Value;


public class VariableExpression implements Expression {
    private String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symbolTable) throws GenericException {
        return symbolTable.get(this.name);
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}

package com.ubb.domain.expressions;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.value.Value;

public class ValueExpression implements Expression {
    Value value;

    public ValueExpression(Value val){
        this.value = val;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> tbl) throws GenericException {
        return value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}

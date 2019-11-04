package com.ubb.domain.expressions;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.value.Value;

public class ValueExpression implements Expression {
    @Override
    public Value evaluate(MyIDictionary<String, Value> tbl) throws GenericException {
        return null; //TODO
    }

    @Override
    public String toString() {
        return ""; //TODO
    }
}

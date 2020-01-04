package com.ubb.domain.expressions;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.type.Type;
import com.ubb.domain.value.Value;

public interface Expression {
    Value evaluate(MyIDictionary<String, Value> tbl, MyIDictionary<Integer, Value> heap) throws GenericException;
    Type typeCheck(MyIDictionary<String,Type> typeEnv) throws GenericException;
}

package com.ubb.domain.value;

import com.ubb.domain.type.IntegerType;
import com.ubb.domain.type.Type;

public class IntegerValue implements Value {
    int val;

    public IntegerValue(int v) {
        val = v;
    }

    public int getVal() {
        return val;
    }

    @Override
    public String toString() {
        return ""; //TODO
    }

    @Override
    public Type getType() {
        return new IntegerType();
    }
}

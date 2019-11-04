package com.ubb.domain.value;

import com.ubb.domain.type.IntType;
import com.ubb.domain.type.Type;

public class IntValue implements Value {
    int val;

    public IntValue(int v) {
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
        return new IntType();
    }
}

package com.ubb.domain.value;

import com.ubb.domain.type.BoolType;
import com.ubb.domain.type.Type;

public class BoolValue implements Value {
    @Override
    public String toString() {
        return ""; //TODO
    }

    @Override
    public Type getType() {
        return new BoolType();
    }
}

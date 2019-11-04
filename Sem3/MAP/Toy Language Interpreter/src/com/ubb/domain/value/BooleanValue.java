package com.ubb.domain.value;

import com.ubb.domain.type.BooleanType;
import com.ubb.domain.type.Type;

public class BooleanValue implements Value {
    private boolean value;

    public BooleanValue(boolean value){
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return ""; //TODO
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }
}

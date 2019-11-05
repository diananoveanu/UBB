package com.ubb.domain.value;

import com.ubb.domain.type.BooleanType;
import com.ubb.domain.type.Type;

public class BooleanValue implements Value {
    private boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof BooleanValue;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }
}

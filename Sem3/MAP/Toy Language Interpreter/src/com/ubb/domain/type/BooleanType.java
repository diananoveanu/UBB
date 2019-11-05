package com.ubb.domain.type;

import com.ubb.domain.value.BooleanValue;
import com.ubb.domain.value.Value;

public class BooleanType implements Type {
    @Override
    public boolean equals(Object another) {
        return another instanceof BooleanType;
    }

    @Override
    public Value defaultValue() {
        return new BooleanValue(false);
    }

    @Override
    public String toString() {
        return "bool";
    }
}

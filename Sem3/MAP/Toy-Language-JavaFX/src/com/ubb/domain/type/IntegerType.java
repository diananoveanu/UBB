package com.ubb.domain.type;

import com.ubb.domain.value.IntegerValue;
import com.ubb.domain.value.Value;

public class IntegerType implements Type {
    @Override
    public boolean equals(Object another) {
        return another instanceof IntegerType;
    }

    @Override
    public Value defaultValue() {
        return new IntegerValue(0);
    }

    @Override
    public String toString() {
        return "int";
    }
}

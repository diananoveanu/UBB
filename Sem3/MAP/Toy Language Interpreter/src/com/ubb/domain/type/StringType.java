package com.ubb.domain.type;

import com.ubb.domain.value.StringValue;
import com.ubb.domain.value.Value;

public class StringType implements Type {
    @Override
    public boolean equals(Object another) {
        return another instanceof StringType;
    }

    @Override
    public Value defaultValue() {
        return new StringValue("");
    }

    @Override
    public String toString() {
        return "string";
    }
}

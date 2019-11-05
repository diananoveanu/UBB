package com.ubb.domain.value;

import com.ubb.domain.type.StringType;
import com.ubb.domain.type.Type;

public class StringValue implements Value {
    String val;

    public StringValue(String v) {
        val = v;
    }

    public String getValue() {
        return val;
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof StringValue;
    }

    @Override
    public String toString() {
        return val;
    }

    @Override
    public Type getType() {
        return new StringType();
    }
}

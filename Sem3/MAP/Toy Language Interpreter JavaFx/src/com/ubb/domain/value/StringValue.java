package com.ubb.domain.value;

import com.ubb.domain.type.StringType;
import com.ubb.domain.type.Type;

import java.util.Objects;

public class StringValue implements Value {
    private String val;

    public StringValue(String v) {
        val = v;
    }

    public String getValue() {
        return val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringValue that = (StringValue) o;
        return Objects.equals(val, that.val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
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

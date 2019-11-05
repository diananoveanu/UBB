package com.ubb.domain.value;

import com.ubb.domain.type.IntegerType;
import com.ubb.domain.type.Type;

import java.util.Objects;

public class IntegerValue implements Value {
    int val;

    public IntegerValue(int v) {
        val = v;
    }

    public int getValue() {
        return val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerValue that = (IntegerValue) o;
        return val == that.val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public String toString() {
        return "" + val;
    }

    @Override
    public Type getType() {
        return new IntegerType();
    }
}

package com.ubb.domain.value;

import com.ubb.domain.type.BooleanType;
import com.ubb.domain.type.Type;

import java.util.Objects;

public class BooleanValue implements Value {
    private boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooleanValue that = (BooleanValue) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
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

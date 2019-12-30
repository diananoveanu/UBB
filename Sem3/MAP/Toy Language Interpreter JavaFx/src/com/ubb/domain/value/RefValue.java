package com.ubb.domain.value;

import com.ubb.domain.type.RefType;
import com.ubb.domain.type.Type;

public class RefValue implements Value {
    private int address;
    private Type locationType;

    public RefValue(Type type, int i) {
        this.address = i;
        this.locationType = type;
    }

    public int getAddress() {
        return this.address;
    }

    @Override
    public String toString() {
        return "(" + address + ", " + locationType + ")";
    }

    public Type getType() {
        return new RefType(locationType);
    }
}

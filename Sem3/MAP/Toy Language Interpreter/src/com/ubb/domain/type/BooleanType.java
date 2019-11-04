package com.ubb.domain.type;

public class BooleanType implements Type {
    @Override
    public boolean equals(Object another) {
        return another instanceof BooleanType;
    }

    @Override
    public String toString() {
        return "bool";
    }
}

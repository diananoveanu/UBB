package com.ubb.domain.type;

public class IntType implements Type {

    public boolean equals(Object another) {
        if (another instanceof IntType) return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "int";
    }
}

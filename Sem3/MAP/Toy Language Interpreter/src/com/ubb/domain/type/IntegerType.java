package com.ubb.domain.type;

public class IntegerType implements Type {

    public boolean equals(Object another) {
        if (another instanceof IntegerType) return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "int";
    }
}

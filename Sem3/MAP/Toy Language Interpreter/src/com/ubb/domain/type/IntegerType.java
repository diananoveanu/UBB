package com.ubb.domain.type;

public class IntegerType implements Type {

    public boolean equals(Object another) {
        return another instanceof IntegerType;
    }

    @Override
    public String toString() {
        return "int";
    }
}

package domain.types;

import domain.values.IntegerValue;
import domain.values.Value;

public class IntegerType implements Type {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntegerType;
    }

    @Override
    public String toString() {
        return "Integer";
    }

    @Override
    public Value getDefaultValue() {
        return new IntegerValue(0);
    }
}

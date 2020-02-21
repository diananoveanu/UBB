package domain.types;

import domain.values.BooleanValue;
import domain.values.Value;

public class BooleanType implements Type {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof BooleanType;
    }

    @Override
    public String toString() {
        return "Boolean";
    }

    @Override
    public Value getDefaultValue() {
        return new BooleanValue(false);
    }
}

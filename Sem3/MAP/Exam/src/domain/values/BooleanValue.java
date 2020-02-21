package domain.values;

import domain.types.BooleanType;
import domain.types.Type;

import java.util.Objects;

public class BooleanValue implements Value {
    Boolean value;

    public BooleanValue(Boolean val) {
        this.value = val;
    }

    public Boolean getValue() {
        return this.value;
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }

    @Override
    public Value deepCopy() {
        return new BooleanValue(value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooleanValue value1 = (BooleanValue) o;
        return Objects.equals(value, value1.value);
    }
}

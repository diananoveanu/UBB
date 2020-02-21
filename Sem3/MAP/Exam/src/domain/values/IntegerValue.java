package domain.values;

import domain.types.IntegerType;
import domain.types.Type;

import java.util.Objects;

public class IntegerValue implements Value {
    Integer value;

    public IntegerValue(Integer integer) {
        this.value = integer;
    }

    public Integer getValue() {
        return this.value;
    }

    @Override
    public Type getType() {
        return new IntegerType();
    }

    @Override
    public Value deepCopy() {
        return new IntegerValue(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerValue that = (IntegerValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}

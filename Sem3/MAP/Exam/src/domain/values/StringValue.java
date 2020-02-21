package domain.values;

import domain.types.StringType;
import domain.types.Type;

import java.util.Objects;

public class StringValue implements Value {
    String value;

    public StringValue(String val) {
        this.value = val;
    }

    public String getValue() {
        return this.value;
    }

    public Type getType() {
        return new StringType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringValue that = (StringValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public Value deepCopy()
    {
        return new StringValue(this.value);
    }
}

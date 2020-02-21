package domain.types;

import domain.values.StringValue;
import domain.values.Value;

public class StringType implements Type {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringType;
    }

    @Override
    public String toString() {
        return "String";
    }

    @Override
    public Value getDefaultValue() {
        return new StringValue("");
    }
}

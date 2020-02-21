package domain.values;

import domain.types.Type;

public interface Value {
    Type getType();

    Value deepCopy();
}

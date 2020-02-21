package domain.types;

import domain.values.ReferenceValue;
import domain.values.Value;

public class ReferenceType implements Type {
    private Type inner;

    public ReferenceType(Type inner) {
        this.inner = inner;
    }

    @Override
    public boolean equals(Object another) {
        if (another instanceof ReferenceType) {
            return inner.equals(((ReferenceType) another).getInner());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Ref " + inner.toString();
    }

    @Override
    public Value getDefaultValue() {
        return new ReferenceValue(inner, 0);
    }

    public Type getInner() {
        return inner;
    }
}

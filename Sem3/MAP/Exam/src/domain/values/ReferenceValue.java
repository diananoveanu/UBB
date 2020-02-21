package domain.values;

import domain.types.ReferenceType;
import domain.types.Type;

public class ReferenceValue implements Value {
    private int address;
    private Type locationType;

    public ReferenceValue(Type type, int i) {
        this.address = i;
        this.locationType = type;
    }

    public int getAddress() {
        return this.address;
    }

    public Type getLocationType() {
        return locationType;
    }

    @Override
    public String toString() {
        return "(" + address + ", " + locationType + ")";
    }

    public Type getType() {
        return new ReferenceType(locationType);
    }

    @Override
    public Value deepCopy() {
        return new ReferenceValue(locationType, address);
    }
}

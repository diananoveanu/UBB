package dtos;

import domain.values.Value;

public class SymDto {

    private String varName;
    private Value value;

    public SymDto(String varName, Value value) {
        this.varName = varName;
        this.value = value;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}

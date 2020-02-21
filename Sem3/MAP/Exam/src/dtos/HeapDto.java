package dtos;

import domain.values.Value;

public class HeapDto {
    private Integer address;
    private Value value;

    public HeapDto(Integer adr, Value val){
        this.address = adr;
        this.value = val;
    }

    public Integer getAddress() {
        return address;
    }

    public Value getValue() {
        return value;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}

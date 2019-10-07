package com.ubb.domain.expressions;

import com.ubb.adt.dict.MyIDictionary;

public class ConstExp extends Exp {
    private Integer number;

    @Override
    Integer eval(MyIDictionary<String, Integer> tbl) {
        return number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number.toString();
    }
}

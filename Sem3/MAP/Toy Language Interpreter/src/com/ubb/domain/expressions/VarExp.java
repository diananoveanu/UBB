package com.ubb.domain.expressions;

import com.ubb.adt.dict.MyIDictionary;

public class VarExp extends Exp {
    String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    Integer eval(MyIDictionary<String, Integer> tbl){
        return tbl.lookup(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

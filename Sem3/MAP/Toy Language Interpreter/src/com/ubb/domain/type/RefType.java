package com.ubb.domain.type;

import com.ubb.domain.value.RefValue;
import com.ubb.domain.value.Value;

public class RefType implements Type {
    private Type inner;

    public RefType(Type inner){
        this.inner = inner;
    }


    @Override
    public boolean equals(Object another){
        if(another instanceof RefType){
            return inner.equals(((RefType) another).getInner());
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return "Ref " + inner.toString();
    }

    @Override
    public Value defaultValue() {
        return new RefValue(inner, 0);
    }

    public Type getInner(){
        return inner;
    }
}

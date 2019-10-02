package com.ubb.model;

public interface ObjectInter<ID, W, T> {
    W getWeight();

    T getType();

    ID getId();

    void setId(ID id);

    void setType(W weight);

    void setWeight(T type);
}

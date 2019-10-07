package com.ubb.adt;

public interface MyIList<T> {
    T getElemOnPos(Integer pos);

    void delete(T elem);

    void add(T elem);

    T[] getAll();
}

package com.ubb.adt.list;

public interface MyIList<T> {
    void delete(T elem);

    void add(T elem);

    T[] getAll();

    Boolean isEmpty();

    T getFromIndex(Integer index);

    void removeAll();

    Integer size();

    Boolean containsValue(T elem);
}

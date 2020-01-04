package com.ubb.adt.stack;

import java.util.List;

public interface MyIStack<T> {
    T pop();

    void push(T v);

    Boolean isEmpty();

    List<T> getAll();
}

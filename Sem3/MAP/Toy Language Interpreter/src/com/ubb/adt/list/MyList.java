package com.ubb.adt.list;

import java.util.ArrayList;

public class MyList<T> implements MyIList<T> {
    private ArrayList<T> list;

    public MyList() {
        list = new ArrayList<T>();
    }

    @Override
    public Integer size() {
        return list.size();
    }

    @Override
    public Boolean containsValue(T elem) {
        return list.contains(elem);
    }

    @Override
    public void delete(T elem) {
        list.remove(elem);
    }



    @Override
    public void add(T elem) {
        list.add(elem);
    }

    @Override
    public Boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void removeAll() {
        list.clear();
    }

    @Override
    public T getFromIndex(Integer index) {
        return list.get(index);
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public T[] getAll() {
        return null;
    }
}

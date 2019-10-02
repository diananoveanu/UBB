package com.ubb.repository;

import com.ubb.model.DuplicateException;

import java.util.ArrayList;

public class Repository<T> {
    private ArrayList<T> objects;

    public void add(T obj) throws DuplicateException {
        for (T t : objects)
            if (t.equals(obj))
                throw new DuplicateException("Object not unique!");
         //TODO implement stuff when requirements are posted
    }

    public int size() {
        return objects.size();
    }

    public ArrayList<T> getObjects() {
        return objects;
    }
}

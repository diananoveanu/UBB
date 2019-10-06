
package com.ubb.repository;

import com.ubb.model.Array;
import com.ubb.model.DuplicateException;
import com.ubb.model.ObjectInter;

public class Repository {
    private Array objects;

    public Repository() {
        objects = new Array();
    }

    public void add(ObjectInter obj) throws DuplicateException {
        for (int i = 0; i < objects.getSize(); i++)
            if (objects.getObjects()[i].getId().equals(obj.getId())) {
                throw new DuplicateException("Object not unique!");
            }
        objects.add(obj);
    }

    public void delete(ObjectInter obj) {
        objects.delete(obj);
    }


    public int size() {
        return objects.getSize();
    }

    public Array getObjects() {
        return objects;
    }
}


package com.ubb.model;

public class Array {
    private ObjectInter[] objects;
    private Integer size;
    private Integer curr;

    public Array() {
        size = 100;
        curr = 0;
        objects = new ObjectInter[size];
    }

    public void add(ObjectInter obj) {
        if (curr < size) {
            objects[curr] = obj;
            curr++;
        } else {
            int newSize = 2 * size;
            ObjectInter[] newElems = new ObjectInter[newSize];
            if (size >= 0) System.arraycopy(objects, 0, newElems, 0, size);
            size = newSize;
            objects = newElems;
            objects[curr] = obj;
            curr++;
        }
    }

    public void delete(ObjectInter obj) {
        int pos = -1;
        for (int i = 0; i < curr; i++) {
            if (obj.getId() == objects[i].getId()) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            return;
        }

        for (int i = pos; i < curr - 1; i++) {
            objects[i] = objects[i + 1];
        }
        curr--;
    }

    public int getSize() {
        return curr;
    }

    public ObjectInter[] getObjects() {
        return objects;
    }

}
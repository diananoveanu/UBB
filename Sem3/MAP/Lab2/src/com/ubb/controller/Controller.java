package com.ubb.controller;

import com.ubb.model.Array;
import com.ubb.model.ObjectInter;
import com.ubb.repository.Repository;


public class Controller {
    private Repository repository;

    public Controller(Repository r) {
        repository = r;
    }

    public Array getAllOver() {
        Array objs = repository.getObjects();
        Array answer = new Array();
        for (int i = 0; i < objs.getSize(); i++) {
            if ((Integer) objs.getObjects()[i].getWeight() > 200) {
                answer.add(objs.getObjects()[i]);
            }
        }
        return answer;
    }

    public void add(ObjectInter obj) {
        try {
            repository.add(obj);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}

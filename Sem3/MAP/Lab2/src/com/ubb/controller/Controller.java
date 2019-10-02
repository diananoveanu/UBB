package com.ubb.controller;

import com.ubb.repository.Repository;

import java.util.ArrayList;

public class Controller<T> {

    private Repository<T> repository;

    public Controller(Repository<T> r) {
        this.repository = r;
    }

    public ArrayList<T> getAll() {
        return repository.getObjects();
    }

    //TODO implement other methods when requirements are giver
}

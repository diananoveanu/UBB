package com.ubb.model;

public class Porc implements ObjectInter<Integer, Integer, String> {
    private Integer id;
    private Integer weight;
    private static String type = "Porc";

    public Porc(Integer i, Integer w) {
        id = i;
        weight = w;
    }

    @Override
    public Integer getWeight() {
        return weight;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer i) {
        id = i;
    }

    @Override
    public void setType(Integer w) {
        weight = w;
    }

    @Override
    public void setWeight(String t) {
        type = t;
    }
}

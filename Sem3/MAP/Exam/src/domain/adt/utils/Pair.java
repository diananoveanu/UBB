package domain.adt.utils;

import java.util.List;

public class Pair {
    private Integer integer;
    private List<Integer> integerList;

    public Pair(Integer integer, List<Integer> integerList) {
        this.integer = integer;
        this.integerList = integerList;
    }

    public Integer getInteger() {
        return integer;
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    @Override
    public String toString() {
        return "(" + integer.toString() + ", " + integerList.toString() + ")";
    }
}

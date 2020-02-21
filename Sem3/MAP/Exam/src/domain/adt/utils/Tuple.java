package domain.adt.utils;

import java.util.List;

public class Tuple {
    private Integer integer;
    private List<Integer> integerList;
    private Integer integer2;

    public Tuple(Integer integer, List<Integer> integerList, Integer integer2) {
        this.integer = integer;
        this.integerList = integerList;
        this.integer2 = integer2;
    }

    public Integer getFirstInteger() {
        return integer;
    }

    public Integer getSecondInteger(){
        return integer2;
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    @Override
    public String toString() {
        return "( " + integer.toString() + ", " + integerList.toString() +", " + integer2.toString() +" )";
    }
}

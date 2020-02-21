package domain.adt.barrier;

import domain.adt.utils.Pair;

import java.util.Map;

public interface IBarrier {

    boolean exist(int key);

    void put(int key, Pair val);

    Pair get(int key);

    boolean containsKey(int key);

    Map<Integer, Pair> getContent();

}

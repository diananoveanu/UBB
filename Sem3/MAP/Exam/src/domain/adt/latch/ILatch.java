package domain.adt.latch;

import java.util.Map;

public interface ILatch {
    boolean exist(int key);

    void put(int key, int value);

    Integer get(int key);

    boolean containsKey(int key);

    Map<Integer, Integer> getContent();
}

package domain.adt.latch;

import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;

import java.util.Map;

public class MyLatch implements ILatch {
    IDictionary<Integer, Integer> latchMap;

    public MyLatch() {
        latchMap = new MyDictionary<>();
    }

    @Override
    public synchronized boolean exist(int key) {
        return latchMap.get(key) != null;
    }

    @Override
    public synchronized void put(int key, int value) {
        latchMap.put(key, value);
    }

    @Override
    public synchronized Integer get(int key) {
        return latchMap.get(key);
    }

    @Override
    public synchronized boolean containsKey(int key) {
        return latchMap.containsKey(key);
    }

    @Override
    public Map<Integer, Integer> getContent() {
        return this.latchMap.getMap();
    }
}

package domain.adt.barrier;

import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;
import domain.adt.utils.Pair;

import java.util.Map;

public class MyBarrier implements IBarrier {
    IDictionary<Integer, Pair> barrierMap;

    public MyBarrier() {
        barrierMap = new MyDictionary<>();
    }

    @Override
    public synchronized boolean exist(int key) {
        return barrierMap.get(key) != null;
    }

    @Override
    public synchronized void put(int key, Pair value) {
        barrierMap.put(key, value);
    }

    @Override
    public synchronized Pair get(int key) {
        return barrierMap.get(key);
    }

    @Override
    public synchronized boolean containsKey(int key) {
        return barrierMap.containsKey(key);
    }

    @Override
    public Map<Integer, Pair> getContent() {
        return barrierMap.getMap();
    }
}

package domain.adt.lock;

import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;

import java.util.Map;

public class MyLock implements ILock {
    IDictionary<Integer, Integer> lockMap;

    public MyLock() {
        lockMap = new MyDictionary<>();
    }

    @Override
    public synchronized boolean exist(int key) {
        return lockMap.get(key) != null;
    }

    @Override
    public synchronized void put(int key, int value) {
        lockMap.put(key, value);
    }

    @Override
    public synchronized Integer get(int key) {
        return lockMap.get(key);
    }

    @Override
    public synchronized boolean containsKey(int key) {
        return lockMap.containsKey(key);
    }

    @Override
    public Map<Integer, Integer> getContent() {
        return this.lockMap.getMap();
    }
}

package domain.adt.heap;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<T> implements IHeap<T> {
    private HashMap<Integer, T> map;
    private int memloc;

    public MyHeap() {
        this.map = new HashMap<>();
        this.memloc = 0;
    }

    public boolean contains(int addr) {
        return map.containsKey(addr);
    }

    @Override
    public int allocate(Object val) {
        this.memloc++;
        this.map.put(this.memloc, (T) val);

        return this.memloc;
    }

    @Override
    public T get(int addr) {
        return this.map.get(addr);
    }

    @Override
    public void put(int addr, Object val) {
        this.map.put(addr, (T) val);
    }

    @Override
    public T deallocate(int addr) {
        return this.map.remove(addr);
    }

    @Override
    public Map<Integer, T> getContent() {
        return this.map;
    }

    @Override
    public void setContent(Map<Integer, T> content) {
        this.map = (HashMap) content;
    }

    @Override
    public String toString() {
        String all = "";

        for (Integer key : this.map.keySet()) {
            all += key + " -> " + map.get(key) + "\n";
        }

        return "Heap:\n" + all;
    }
}

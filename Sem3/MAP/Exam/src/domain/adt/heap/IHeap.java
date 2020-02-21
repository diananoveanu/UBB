package domain.adt.heap;

import java.util.Map;

public interface IHeap<T> {
    public int allocate(Object val);

    public T get(int addr);

    public void put(int addr, Object val);

    public T deallocate(int addr);

    public Map<Integer, T> getContent();

    public void setContent(Map<Integer, T> content);

}

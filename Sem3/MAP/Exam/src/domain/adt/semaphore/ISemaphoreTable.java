package domain.adt.semaphore;

import domain.adt.dictionary.IDictionary;
import domain.adt.utils.Pair;

import java.util.Map;

public interface ISemaphoreTable {

    void setSemaphores(IDictionary<Integer, Pair> semaphores);

    Integer getSemaphoreAddress();

    boolean exists(Integer index);

    IDictionary<Integer, Pair> getSemaphore();

    void put(Integer foundIndex, Pair integerListPair);

    public Map<Integer, Pair> getContent();

}

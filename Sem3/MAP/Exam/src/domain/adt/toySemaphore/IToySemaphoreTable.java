package domain.adt.toySemaphore;

import domain.adt.dictionary.IDictionary;
import domain.adt.utils.Tuple;

import java.util.Map;

public interface IToySemaphoreTable {
    void setSemaphores(IDictionary<Integer, Tuple> semaphores);

    Integer getSemaphoreAddress();

    boolean exists(Integer index);

    IDictionary<Integer, Tuple> getSemaphore();

    void put(Integer foundIndex, Tuple integerListPair);

    Map<Integer, Tuple> getContent();

}

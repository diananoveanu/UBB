package domain.adt.semaphore;

import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;
import domain.adt.utils.AddressBuilder;
import domain.adt.utils.Pair;

import java.util.Map;

public class MySemaphoreTable implements ISemaphoreTable {
    IDictionary<Integer, Pair> semaphore;
    private AddressBuilder semaphoreAddress = new AddressBuilder();

    public MySemaphoreTable(){
        semaphore = new MyDictionary<>();
    }

    @Override
    public void setSemaphores(IDictionary<Integer, Pair> semaphores) {
        this.semaphore = semaphores;

    }

    @Override
    public synchronized boolean exists(Integer index)
    {
        Pair pairValue = semaphore.get(index);
        return pairValue != null;
    }

    @Override
    public Integer getSemaphoreAddress() {
        return semaphoreAddress.getFreeAddress();
    }

    @Override
    public IDictionary<Integer, Pair> getSemaphore() {
        return semaphore;
    }

    @Override
    public synchronized void put(Integer foundIndex, Pair integerListPair) {
        semaphore.put(foundIndex, integerListPair);
    }

    @Override
    public Map<Integer, Pair> getContent() {
        return semaphore.getMap();
    }
}

package domain.adt.toySemaphore;

import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;
import domain.adt.utils.AddressBuilder;
import domain.adt.utils.Tuple;

import java.util.Map;

public class MyToySemaphoreTable implements IToySemaphoreTable {

    IDictionary<Integer, Tuple> semaphore;
    private AddressBuilder semaphoreAddress = new AddressBuilder();

    public MyToySemaphoreTable() {
        semaphore = new MyDictionary<>();
    }

    @Override
    public void setSemaphores(IDictionary<Integer, Tuple> semaphores) {
        this.semaphore = semaphores;

    }

    @Override
    public synchronized boolean exists(Integer index) {
        Tuple pairValue = semaphore.get(index);
        return pairValue != null;
    }

    @Override
    public Integer getSemaphoreAddress() {
        return semaphoreAddress.getFreeAddress();
    }

    @Override
    public IDictionary<Integer, Tuple> getSemaphore() {
        return semaphore;
    }

    @Override
    public synchronized void put(Integer foundIndex, Tuple integerListIntegerTuple) {
        semaphore.put(foundIndex, integerListIntegerTuple);
    }

    @Override
    public Map<Integer,Tuple> getContent(){
        return this.semaphore.getMap();
    }
}

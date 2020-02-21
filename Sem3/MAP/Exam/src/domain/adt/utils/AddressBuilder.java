package domain.adt.utils;

import domain.adt.stack.IStack;
import domain.adt.stack.MyStack;

public class AddressBuilder {
    private Integer address = 1;
    private static IStack<Integer> freeAddress = new MyStack<>();

    public Integer getFreeAddress() {
        return freeAddress.isEmpty() ? this.address ++ : freeAddress.pop();
    }
}

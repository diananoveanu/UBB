package domain.adt.stack;

import java.util.List;

public interface IStack<T> {
    T pop();

    void push(T v);

    Boolean isEmpty();


    List<T> getAll();
}

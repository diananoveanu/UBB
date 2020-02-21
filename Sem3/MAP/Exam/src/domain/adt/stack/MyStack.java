package domain.adt.stack;

import java.util.List;
import java.util.Stack;

public class MyStack<T> implements IStack<T> {
    private Stack<T> stack;

    public MyStack() {
        stack = new Stack<T>();
    }

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public Boolean isEmpty() {
        return stack.empty();
    }

    @Override
    public List<T> getAll() {
        return stack;
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}

package com.ubb.adt.stack;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
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
    public String toString() {
        return stack.toString();
    }
}

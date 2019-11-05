package com.ubb.domain;

import com.ubb.adt.dictionary.MyDictionary;
import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.adt.list.MyIList;
import com.ubb.adt.list.MyList;
import com.ubb.adt.stack.MyIStack;
import com.ubb.adt.stack.MyStack;
import com.ubb.domain.statements.IStatement;
import com.ubb.domain.value.StringValue;
import com.ubb.domain.value.Value;

import java.io.BufferedReader;
import java.util.ArrayList;

public class ProgramState {
    MyIStack<IStatement> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyIDictionary<StringValue, BufferedReader> fileTable;
    IStatement originalProgram;

    public ProgramState(MyIStack<IStatement> exeStack, MyIDictionary<String, Value> symTable, MyIList<Value> out, IStatement originalProgram) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram;
        exeStack.push(originalProgram);
    }

    public ProgramState(IStatement originalProgram){
        exeStack = new MyStack<>();
        symTable = new MyDictionary<>();
        out = new MyList<>();
        this.originalProgram = originalProgram;
        exeStack.push(originalProgram);
        fileTable = new MyDictionary<>();
    }



    public MyIStack<IStatement> getExeStack() {
        return exeStack;
    }

    public void setFileTable(MyIDictionary<StringValue, BufferedReader> newFileTable) {this.fileTable = newFileTable;}

    public void setExeStack(MyIStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public void addOut(Value value){
        this.out.add(value);
    }

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    @Override
    public String toString() {
        return this.originalProgram.toString();
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return this.fileTable;
    }
}

package com.ubb.domain;

import com.ubb.adt.dictionary.MyDictionary;
import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.adt.list.MyIList;
import com.ubb.adt.list.MyList;
import com.ubb.adt.stack.MyIStack;
import com.ubb.adt.stack.MyStack;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.statements.IStatement;
import com.ubb.domain.value.StringValue;
import com.ubb.domain.value.Value;

import java.io.BufferedReader;

public class ProgramState {
    private MyIStack<IStatement> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;
    private MyIDictionary<StringValue, BufferedReader> fileTable;
    private IStatement originalProgram;
    private MyIDictionary<Integer, Value> heap;
    private Integer progId = 1;


    public ProgramState(MyIStack<IStatement> exeStack, MyIDictionary<String, Value> symTable, MyIList<Value> out,
                        MyIDictionary<StringValue, BufferedReader> fileTable, MyIDictionary<Integer, Value> heap, IStatement originalProgram, int id) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram;
        this.fileTable = fileTable;
        this.heap = heap;
        this.progId = id;
        this.exeStack.push(originalProgram);
    }

    public ProgramState(IStatement originalProgram){
        exeStack = new MyStack<>();
        symTable = new MyDictionary<>();
        out = new MyList<>();
        this.originalProgram = originalProgram;
        exeStack.push(originalProgram);
        fileTable = new MyDictionary<>();
        heap = new MyDictionary<>();

    }



    public synchronized Integer getId(){
        return progId;
    }

    public synchronized void setId(Integer newId){
        this.progId = newId;
    }

    public MyIStack<IStatement> getExeStack() {
        return exeStack;
    }

    public void setHeap(MyIDictionary<Integer, Value> newHeap) {this.heap = newHeap;}

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

    public MyIDictionary<Integer, Value> getHeap() {return heap;}

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    public Boolean isNotCompleted(){
        return !this.exeStack.isEmpty();
    }

    public ProgramState oneStep() throws GenericException {
        if(exeStack.isEmpty()) throw new GenericException("Exe Stack is empty!");

        IStatement crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    @Override
    public String toString() {
        return "#######################################################\n" +
                "===== PROGRAM ID ====\n" + progId + "\n" +
                "===== EXE STACK =====\n" + exeStack.toString() +"\n" +
                "===== SYM TABLE =====\n" + symTable.toString() +"\n" +
                "===== OUT TABLE =====\n" + out.toString() + "\n" +
                "===== FILE TABLE =====\n" + fileTable.toString() +"\n" +
                "===== HEAP =====\n" + heap.toString()+ "\n" +
                "#######################################################\n\n";
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return this.fileTable;
    }
}

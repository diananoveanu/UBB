package domain;

import domain.adt.barrier.MyBarrier;
import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;
import domain.adt.heap.IHeap;
import domain.adt.heap.MyHeap;
import domain.adt.latch.MyLatch;
import domain.adt.list.IList;
import domain.adt.list.MyList;
import domain.adt.lock.MyLock;
import domain.adt.procedure.MyProcedure;
import domain.adt.semaphore.MySemaphoreTable;
import domain.adt.stack.IStack;
import domain.adt.stack.MyStack;
import domain.adt.toySemaphore.MyToySemaphoreTable;
import domain.adt.utils.AddressBuilder;
import domain.exceptions.GenericException;
import domain.exceptions.statementExecutionExceptions.EmptyStackException;
import domain.statements.IStatement;
import domain.values.StringValue;
import domain.values.Value;

import java.io.BufferedReader;
import java.util.Stack;

public class ProgramState {
    IStack<IStatement> executionStack;
    Stack<IDictionary<String, Value>> symbolTables;
    IList<Value> out;
    IDictionary<StringValue, BufferedReader> fileTable;
    IStatement initialProgram;
    IHeap<Value> heapTable;
    MySemaphoreTable semaphoreTable;
    MyToySemaphoreTable toySemaphoreTable;
    MyLock lockTable;
    MyBarrier barrierTable;
    MyLatch latchTable;
    MyProcedure procedureTable;
    private AddressBuilder lockAdrBuilder = new AddressBuilder();
    private AddressBuilder barrierAdrBuilder = new AddressBuilder();
    private AddressBuilder latchAdrBuilder = new AddressBuilder();

    private static int lastId = 0;
    private int Id;

    public ProgramState(IStatement initialProgram) {
        executionStack = new MyStack<>();
        symbolTables = new Stack<>();
        symbolTables.push(new MyDictionary<>());
        //symbolTable = new MyDictionary<>();
        out = new MyList<>();
        this.initialProgram = initialProgram;
        executionStack.push(initialProgram);
        fileTable = new MyDictionary<>();
        heapTable = new MyHeap<>();
        semaphoreTable = new MySemaphoreTable();
        lockTable = new MyLock();
        barrierTable = new MyBarrier();
        latchTable = new MyLatch();
        toySemaphoreTable = new MyToySemaphoreTable();
        procedureTable = new MyProcedure();
    }

    @Override
    public String toString() {
        return "#######################################################\n" +
                "===== PROGRAM ID =====\n" + Id + "\n" +
                "===== EXE STACK =====\n" + executionStack.toString() + "\n" +
                "===== HEAP TABLE =====\n" + heapTable.toString() + "\n" +
                "===== SYM TABLE =====\n" + symbolTables.peek().toString() + "\n" +
                "===== OUT TABLE =====\n" + out.toString() + "\n" +
                "===== FILE TABLE =====\n" + fileTable.toString() + "\n" +
                "===== INITIAL PROGRAM =====\n" + initialProgram.toString() + "\n" +
                "#######################################################\n\n";
    }


    public ProgramState(IStack<IStatement> stk, Stack<IDictionary<String, Value>> symtbl, IList<Value> out,
                        IStatement prg, IDictionary<StringValue, BufferedReader> fileTable,
                        IHeap<Value> heap, MySemaphoreTable semaphoreTable, MyLock lockTable,
                        MyBarrier barrierTable, MyLatch latchTable, MyToySemaphoreTable toySemaphoreTable, MyProcedure procedures) {
        this.executionStack = stk;
        this.symbolTables = symtbl;
        this.out = out;
        this.fileTable = fileTable;
        this.initialProgram = prg;
        this.heapTable = heap;
        this.executionStack.push(prg);
        this.semaphoreTable = semaphoreTable;
        this.lockTable = lockTable;
        this.barrierTable = barrierTable;
        this.latchTable = latchTable;
        this.toySemaphoreTable = toySemaphoreTable;
        this.procedureTable = procedures;
    }

    public MyLatch getLatchTable(){
        return latchTable;
    }

    public Integer getFreeLockAddress() {
        return lockAdrBuilder.getFreeAddress();

    }

    public Integer getFreeLatchAddress(){
        return latchAdrBuilder.getFreeAddress();
    }

    public Integer getFreeBarrierAddress(){
        return barrierAdrBuilder.getFreeAddress();
    }

    public ProgramState oneStep() throws GenericException {
        if (executionStack.isEmpty()) {
            throw new EmptyStackException("ProgramState stack is empty!");
        }
        IStatement currStmt = executionStack.pop();
        return currStmt.execute(this);
    }

    public boolean noSteps() {
        return executionStack.isEmpty();
    }

    public boolean isNotCompleted() {
        return !this.executionStack.isEmpty();
    }

    public synchronized void setNewId() {
        lastId++;
        this.Id = lastId;
    }

    public Stack<IDictionary<String, Value>> getSymbolTables(){
        return symbolTables;
    }

    public MyToySemaphoreTable getToySemaphoreTable(){
        return toySemaphoreTable;
    }

    public MyLock getLockTable() {
        return this.lockTable;
    }

    public Integer getID() {
        return this.Id;
    }

    public MyBarrier getBarrierTable(){
        return barrierTable;
    }

    public MySemaphoreTable getSemaphoreTable() {
        return semaphoreTable;
    }

    public IStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public void setExecutionStack(IStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public IDictionary<String, Value> getSymbolTable() {
        return symbolTables.peek();
    }

    public void setSymbolTable(Stack<IDictionary<String, Value>> symbolTable) {
        this.symbolTables = symbolTable;
    }

    public IList<Value> getOut() {
        return out;
    }

    public void setOut(IList<Value> out) {
        this.out = out;
    }

    public IStatement getInitialProgram() {
        return initialProgram;
    }

    public void setInitialProgram(IStatement initialProgram) {
        this.initialProgram = initialProgram;
    }

    public IDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(IDictionary<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public IHeap<Value> getHeapTable() {
        return heapTable;
    }

    public void setHeapTable(IHeap<Value> heapTable) {
        this.heapTable = heapTable;
    }

    public MyProcedure getProcedureTable(){
        return procedureTable;
    }

    public ProgramState deepCopy() {
        return new ProgramState(executionStack, symbolTables, out, initialProgram,
                fileTable, heapTable, semaphoreTable, lockTable, barrierTable, latchTable, toySemaphoreTable, procedureTable);
    }
}

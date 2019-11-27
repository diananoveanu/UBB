package com.ubb.controller;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.adt.list.MyIList;
import com.ubb.adt.stack.MyIStack;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.statements.IStatement;
import com.ubb.domain.value.RefValue;
import com.ubb.domain.value.Value;
import com.ubb.repository.IRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class Controller {
    private IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    private Set conservativeGarbageCollector(Collection<Integer> symbolTableValues, MyIDictionary<Integer, Value> heapTable) {
        return heapTable.entrySet().stream().filter(e -> symbolTableValues.contains(e.getKey()))
                .collect(Collectors.toSet());
    }

    List<Integer> getAddrFromSymTable(Collection<Value> syTableValues) {
        return syTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }

    ProgramState oneStep(ProgramState state) throws GenericException {
        MyIStack<IStatement> stk = state.getExeStack();
        if (stk.isEmpty()) throw new GenericException("Program state stack is empty");
        IStatement crtStmt = stk.pop();

        return crtStmt.execute(state);
    }

    public void allStep() throws GenericException, IOException {
        ProgramState prg = repository.getCurrentProgram();
        // repo is the controller field of type MyRepoInterface
        // here you can display the prg state
        this.repository.logPrgStateExec(prg);
        while (!prg.getExeStack().isEmpty()) {
            oneStep(prg);
            prg.getHeap().setContent(conservativeGarbageCollector(getAddrFromSymTable(prg.getSymTable().values()), prg.getHeap()));
            this.repository.logPrgStateExec(this.repository.getCurrentProgram());
        }
        //here you can display the prg state
    }

    public void printPrg() {
        ProgramState prg = repository.getCurrentProgram();
        System.out.println(prg);
    }
}

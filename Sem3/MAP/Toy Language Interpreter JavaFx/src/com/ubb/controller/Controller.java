package com.ubb.controller;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.value.RefValue;
import com.ubb.domain.value.Value;
import com.ubb.repository.IRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class Controller {
    private IRepository repository;
    private ExecutorService executor;
    public Controller(IRepository repository) {
        this.repository = repository;
    }

    private Set conservativeGarbageCollector(Collection<Integer> symbolTableValues, MyIDictionary<Integer, Value> heapTable) {
        return heapTable.entrySet().stream().filter(e -> symbolTableValues.contains(e.getKey()))
                .collect(Collectors.toSet());
    }

    private List<Integer> getAddrFromSymTable(Collection<Value> syTableValues) {
        return syTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private void oneStepForAllProgram(List<ProgramState> states) throws InterruptedException {
        states.forEach(prg -> {
            //printThings();
            repository.logPrgStateExec(prg);
        });

        // create futures
        List<Callable<ProgramState>> callList = states.stream().filter(p->!p.getExeStack().isEmpty())
                .map((ProgramState p) ->

                        (Callable<ProgramState>)(()->{
                            try{
                                return p.oneStep();}
                            catch (GenericException e){
                                System.out.println(e.getMessage());
                                return null;
                            }
                        })
                ).collect(Collectors.toList());
        List<ProgramState> newProgramList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println("End of program");
                    }
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toList());
        states.addAll(newProgramList);
        states.forEach(prg -> {
            prg.getHeap().setContent(conservativeGarbageCollector(
                    getAddrFromSymTable(prg.getSymTable().values()), prg.getHeap()));
        });
        states.forEach(p-> repository.logPrgStateExec(p));
        repository.setProgramStateList(states);
    }


    public void allStep() throws InterruptedException, IOException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> states = removeCompletedProgram(repository.getProgramStateList());
        // List<ProgramState> prgList = null;
        while(states.size() > 0){
            oneStepForAllProgram(states);
            states = removeCompletedProgram(repository.getProgramStateList());
        }
        //closeAllFiles(repository.getCurrentProgram().getFileTable().keySet(), repository.getCurrentProgram().getSymbolTable(), repository.getCurrentProgram());
        repository.logPrgStateExec(repository.getCurrentProgram());

        executor.shutdownNow();
        repository.setProgramStateList(states);

    }

    private List<ProgramState> removeCompletedProgram(List<ProgramState> inProgramList)
    {
        return inProgramList
                .stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public void printPrg() {
        ProgramState prg = repository.getCurrentProgram();
        System.out.println(prg);
    }
}

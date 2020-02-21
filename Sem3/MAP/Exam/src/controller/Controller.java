package controller;

import domain.ProgramState;
import domain.exceptions.GenericException;
import domain.values.ReferenceValue;
import domain.values.Value;
import repository.IRepository;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    IRepository repository;
    ExecutorService executorService;

    public Controller(IRepository repo) {
        this.repository = repo;
    }


    private Map<Integer, Value> unsafeGarbageCollector(List<Integer> addressesFromSymtable, Map<Integer, Value> heap) {
        return heap.entrySet()
                .stream()
                .filter(e -> addressesFromSymtable.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddrFromSymTable(Collection<Value> symTableValues, Collection<Value> heap) {
        return Stream.concat(
                heap.stream()
                        .filter(v -> v instanceof ReferenceValue)
                        .map(v -> {
                            ReferenceValue v1 = (ReferenceValue) v;
                            return v1.getAddress();
                        }),
                symTableValues.stream()
                        .filter(v -> v instanceof ReferenceValue)
                        .map(v -> {
                            ReferenceValue v1 = (ReferenceValue) v;
                            return v1.getAddress();
                        })
        ).collect(Collectors.toList());

    }

    public void oneStepForAllProgram(List<ProgramState> progList) throws InterruptedException {
        progList.forEach(prog -> {
            try {
                repository.logProgramStateExecution(prog);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //PREPARE CALLABLE LIST
        List<Callable<ProgramState>> callableList = progList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>) (
                        p::oneStep
                ))
                .collect(Collectors.toList());

        //Start the execution

        List<ProgramState> newProgList = executorService.invokeAll(callableList).stream()
                .map(futureObj -> {
                    try {
                        return futureObj.get();
                    } catch (ExecutionException | InterruptedException ignored) {
                        System.out.println(ignored.getMessage());
                    }
                    return null;
                })
                .filter(Objects::nonNull).collect(Collectors.toList());

        progList.addAll(newProgList);

        progList.forEach(prg -> {
            try {
                repository.logProgramStateExecution(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        repository.setProgramList(progList);
    }

    public void allStep() throws InterruptedException, GenericException {
        executorService = Executors.newFixedThreadPool(2);

        List<ProgramState> prgList = removeCompletedProgram(repository.getProgramStates());

        while (prgList.size() > 0) {
            oneStepForAllProgram(prgList);
            prgList = removeCompletedProgram(repository.getProgramStates());
        }

        executorService.shutdown();
        repository.setProgramList(prgList);
    }

    public void executeOneStep() {
        executorService = Executors.newFixedThreadPool(8);
        removeCompletedProgram(repository.getProgramStates());
        List<ProgramState> programStates = repository.getProgramStates();
        if (programStates.size() > 0) {
            try {
                oneStepForAllProgram(repository.getProgramStates());
            } catch (InterruptedException e) {
                System.out.println();
            }
            programStates.forEach(e -> {
                try {
                    repository.logProgramStateExecution(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            removeCompletedProgram(repository.getProgramStates());
            executorService.shutdownNow();
        }
    }

    String displayProgramState(ProgramState programState) {
        return programState.toString();
    }

    private List<ProgramState> removeCompletedProgram(List<ProgramState> states) {
        return states.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public ProgramState getProgram() {
        return repository.getCurrentProgram();
    }

    public List<ProgramState> getProgramStates() {
        return repository.getProgramStates();
    }

    public List<String> getIdentifiers() {
        List<String> identifiers = new ArrayList<>();
        repository.getProgramStates().forEach(x -> identifiers.add(x.getID().toString()));
        return identifiers;
    }
}

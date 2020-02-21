package repository;

import domain.ProgramState;
import domain.exceptions.GenericException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Repository implements IRepository {
    private List<ProgramState> programStatesList;
    private String logFilePath;

    public Repository(List<ProgramState> programStateList, String logFilePath) {
        this.programStatesList = programStateList;
        this.logFilePath = logFilePath;
    }

    @Override
    public ProgramState getCurrentProgram() {
        return programStatesList.get(programStatesList.size() - 1);
    }

    @Override
    public void logProgramStateExecution(ProgramState prgState) throws IOException, GenericException {
        PrintWriter logFile = null;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        assert logFile != null;
        logFile.write(prgState.toString());
        logFile.close();
    }

    @Override
    public void setProgramList(List<ProgramState> states) {
        this.programStatesList = states;
    }

    @Override
    public List<ProgramState> getProgramStates() {
        return programStatesList;
    }
}

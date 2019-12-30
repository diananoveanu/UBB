package com.ubb.repository;

import com.ubb.domain.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<ProgramState> programStateList;
    private String logFilePath;

    public Repository() {
        programStateList = new ArrayList<>();
        logFilePath = "";
    }

    public Repository(String logFilePath) {
        programStateList = new ArrayList<>();
        this.logFilePath = logFilePath;
    }

    public Repository(List<ProgramState> programStateList, String logFilePath) {
        this.programStateList = programStateList;
        this.logFilePath = logFilePath;
    }

    @Override
    public void setProgramStateList(List<ProgramState> programStateList) {
        this.programStateList = programStateList;
    }

    @Override
    public List<ProgramState> getProgramStateList() {
        return programStateList;
    }

    @Override
    public ProgramState getCurrentProgram() {
        return programStateList.get(programStateList.size() - 1);
    }

    @Override
    public void logPrgStateExec(ProgramState prgState) {
        PrintWriter logFile = null;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert logFile != null;
        logFile.write(prgState.toString());
        logFile.close();
    }
}
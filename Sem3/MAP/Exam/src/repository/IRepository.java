package repository;

import domain.ProgramState;
import domain.exceptions.GenericException;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    ProgramState getCurrentProgram();

    //    List<ProgramState> getProgramStateList();
//    void setProgramStateList(List<ProgramState> programStateList);
    void logProgramStateExecution(ProgramState prgState) throws GenericException, IOException;
    void setProgramList(List<ProgramState> states);
    public List<ProgramState> getProgramStates();
}

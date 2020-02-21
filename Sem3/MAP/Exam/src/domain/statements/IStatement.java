package domain.statements;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;
import domain.exceptions.GenericException;
import domain.types.Type;

public interface IStatement {
    ProgramState execute(ProgramState state) throws GenericException;

    IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws
            GenericException;
}

package domain.statements.exam.procedures;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.exceptions.GenericException;
import domain.statements.IStatement;
import domain.types.Type;

public class ReturnStatement implements IStatement {


    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        state.getSymbolTables().pop();
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        return null;
    }
}

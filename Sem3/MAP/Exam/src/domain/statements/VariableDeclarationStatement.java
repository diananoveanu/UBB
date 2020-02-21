package domain.statements;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.exceptions.GenericException;
import domain.exceptions.statementExecutionExceptions.VariableAlreadyDeclaredException;
import domain.types.Type;

public class VariableDeclarationStatement implements IStatement {
    Type type;
    String id;

    public VariableDeclarationStatement(String v, Type type) {
        this.id = v;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        if (!state.getSymbolTable().containsKey(id)) {
            state.getSymbolTable().put(id, type.getDefaultValue());

        } else throw new VariableAlreadyDeclaredException("Variable " + id + " already declared");
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        typeEnv.put(id, type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return type.toString() + " " + id;
    }
}

package domain.statements.file;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;
import domain.exceptions.GenericException;
import domain.exceptions.expressionEvaluationExceptions.IncorrectTypeException;
import domain.exceptions.statementExecutionExceptions.FileAlreadyOpenException;
import domain.expressions.Expression;
import domain.statements.IStatement;
import domain.types.StringType;
import domain.types.Type;
import domain.values.StringValue;
import domain.values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFileStatement implements IStatement {
    Expression e;

    public OpenReadFileStatement(Expression e) {
        this.e = e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        Value value = e.evaluate(state.getSymbolTable(), state.getHeapTable());
        if (value.getType().equals(new StringType())) {
            StringValue val = (StringValue) value;
            if (!state.getFileTable().containsKey(val)) {
                try {
                    BufferedReader b = new BufferedReader(new FileReader(val.getValue()));
                    state.getFileTable().put(val, b);
                    return null;
                } catch (FileNotFoundException e) {
                    throw new domain.exceptions.statementExecutionExceptions.FileNotFoundException("Error while trying to open the file " + val);
                }
            } else throw new FileAlreadyOpenException("File " + val + " is already open.");
        } else throw new IncorrectTypeException("Filename must be string " + " was given " + value.getType());

    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        Type typeExp = e.typeCheck(typeEnv);
        if (typeExp.equals(new StringType())) return typeEnv;
        else throw new GenericException("The PATH is not a string value");
    }

    @Override
    public String toString() {
        return "openFile(" + e.toString() + ")";
    }
}

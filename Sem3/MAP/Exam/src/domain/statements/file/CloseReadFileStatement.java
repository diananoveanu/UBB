package domain.statements.file;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.exceptions.GenericException;
import domain.exceptions.statementExecutionExceptions.FileNotFoundException;
import domain.expressions.Expression;
import domain.statements.IStatement;
import domain.types.StringType;
import domain.types.Type;
import domain.values.StringValue;
import domain.values.Value;

import java.io.IOException;

public class CloseReadFileStatement implements IStatement {
    Expression e;

    public CloseReadFileStatement(Expression e) {
        this.e = e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        Value val = e.evaluate(state.getSymbolTable(), state.getHeapTable());
        if (val.getType().equals(new StringType())) {
            StringValue stringValue = (StringValue) val;
            if (state.getFileTable().containsKey(stringValue)) {
                try {
                    state.getFileTable().get(stringValue).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                state.getFileTable().remove(stringValue);
                return null;
            } else throw new FileNotFoundException("File " + val + " doesn't exist.");
        } else {
            throw new GenericException("Expression is not string type!");
        }
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        Type typeExp = e.typeCheck(typeEnv);
        if (typeExp.equals(new StringType())) {
            return typeEnv;
        } else
            throw new GenericException("The PATH is not a string value");
    }

    @Override
    public String toString() {
        return "closeFile(" + e.toString() + ")";
    }
}

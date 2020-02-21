package domain.statements.file;

import domain.ProgramState;
import domain.adt.dictionary.IDictionary;
import domain.adt.dictionary.MyDictionary;
import domain.exceptions.GenericException;
import domain.exceptions.expressionEvaluationExceptions.IncorrectTypeException;
import domain.exceptions.statementExecutionExceptions.FileNotFoundException;
import domain.exceptions.statementExecutionExceptions.VariableNotDeclaredException;
import domain.expressions.Expression;
import domain.statements.IStatement;
import domain.types.IntegerType;
import domain.types.StringType;
import domain.types.Type;
import domain.values.IntegerValue;
import domain.values.StringValue;

import java.io.IOException;

public class ReadFileStatement implements IStatement {
    String variableName;
    Expression e;

    public ReadFileStatement(String variableName, Expression e) {
        this.variableName = variableName;
        this.e = e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        if (state.getSymbolTable().containsKey(variableName)) {
            if (state.getSymbolTable().get(variableName).getType().equals(new IntegerType())) {
                if (e.evaluate(state.getSymbolTable(), state.getHeapTable()).getType().equals(new StringType())) {
                    if (state.getFileTable().containsKey((StringValue) e.evaluate(state.getSymbolTable(), state.getHeapTable()))) {
                        try {
                            String line = state.getFileTable().get((StringValue) e.evaluate(state.getSymbolTable(), state.getHeapTable())).readLine();
                            if (line == null) {
                                state.getSymbolTable().put(variableName, new IntegerValue(0));
                            } else {
                                state.getSymbolTable().put(variableName, new IntegerValue(Integer.parseInt(line)));
                            }
                            state.getFileTable().put((StringValue) e.evaluate(state.getSymbolTable(), state.getHeapTable()), state.getFileTable().get((StringValue) e.evaluate(state.getSymbolTable(), state.getHeapTable())));
                            return null;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        throw new FileNotFoundException("There is no file in fileTable with that name!");
                    }
                } else {
                    throw new IncorrectTypeException("Path variable is not a string type!");
                }
            } else {
                throw new IncorrectTypeException("Variable is not an integer!");
            }
        } else {
            throw new VariableNotDeclaredException("Variable is not declared");
        }
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnv) throws GenericException {
        Type typeExp = e.typeCheck(typeEnv);
        if (typeExp.equals(new StringType())) return typeEnv;
        else throw new GenericException("The PATH is not a string value");
    }

    @Override
    public String toString() {
        return "readFile(" + e.toString() + ", " + variableName + ")";
    }
}

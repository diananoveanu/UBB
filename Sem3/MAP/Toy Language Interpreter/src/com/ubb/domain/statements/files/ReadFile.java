package com.ubb.domain.statements.files;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.expressions.Expression;
import com.ubb.domain.statements.IStatement;
import com.ubb.domain.type.BooleanType;
import com.ubb.domain.type.IntegerType;
import com.ubb.domain.type.StringType;
import com.ubb.domain.type.Type;
import com.ubb.domain.value.IntegerValue;
import com.ubb.domain.value.StringValue;
import com.ubb.domain.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement {
    private Expression path;
    private String varName;

    public ReadFile(Expression exp, String var) {
        this.path = exp;
        this.varName = var;
    }

    public String getVarName() {
        return varName;
    }

    public Expression getPath() {
        return path;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if (symTable.containsKey(varName)) {
            Value val = symTable.get(varName);
            if (val.getType().equals(new IntegerType())) {
                Value pathVal = path.evaluate(symTable, state.getHeap());
                if (pathVal.getType().equals(new StringType())) {

                    if (state.getFileTable().containsKey((StringValue) pathVal)) {
                        BufferedReader b = state.getFileTable().get((StringValue) pathVal);
                        try {
                            String line = b.readLine();
                            if (line == null) {
                                state.getSymTable().put(varName, new IntegerValue(0));
                            } else {
                                state.getSymTable().put(varName, new IntegerValue(Integer.parseInt(line)));
                            }
                            state.getFileTable().put((StringValue) pathVal, b);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        throw new GenericException("There is no file in fileTable with that name!");
                    }
                } else {
                    throw new GenericException("Path variable is not a string type!");
                }

            } else {
                throw new GenericException("Variable is not an integer!");
            }
        } else {
            throw new GenericException("Variable is not declared");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws GenericException {
        Type typeExp = path.typeCheck(typeEnv);
        if (typeExp.equals(new StringType())) {
            return typeEnv;
        } else
            throw new GenericException("The PATH is not a string value");
    }


    @Override
    public String toString() {
        return "readFile(" + path.toString() + ", " + varName + ")";
    }
}

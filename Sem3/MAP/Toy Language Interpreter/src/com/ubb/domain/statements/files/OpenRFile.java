package com.ubb.domain.statements.files;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.expressions.Expression;
import com.ubb.domain.statements.IStatement;
import com.ubb.domain.type.StringType;
import com.ubb.domain.value.StringValue;
import com.ubb.domain.value.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFile implements IStatement {
    Expression exp;

    public OpenRFile(Expression exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        Value val = exp.evaluate(state.getSymTable());
        if (val.getType().equals(new StringType())) {
            String path = ((StringValue) val).getValue();
            try {
                BufferedReader b = new BufferedReader(new FileReader(path));
                MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
                if (fileTable.containsKey((StringValue) val)) {
                    throw new GenericException("File already opened!");
                }
                fileTable.put((StringValue) val, b);
            } catch (FileNotFoundException e) {
                throw new GenericException("Error while trying to open the file\n");
            }
        } else {
            throw new GenericException("Expression is not a string type!");
        }
        return state;
    }

    @Override
    public String toString() {
        return "openFile(" + exp.toString() + ")";
    }
    //still in implementation
}

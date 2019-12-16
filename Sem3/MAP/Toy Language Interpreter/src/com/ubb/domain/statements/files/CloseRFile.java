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
import java.io.IOException;

public class CloseRFile implements IStatement {
    private Expression exp;

    public CloseRFile(Expression exp) {
        this.exp = exp;
    }


    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        Value val = exp.evaluate(state.getSymTable(), state.getHeap());
        if (val.getType().equals(new StringType())) {
            StringValue stringValue = (StringValue) val;
            MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();

            if (!fileTable.containsKey(stringValue)) {
                throw new GenericException("There is no file opened with that name!");
            } else {
                BufferedReader b = fileTable.get(stringValue);
                try {
                    b.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fileTable.remove(stringValue);
            }
        } else {
            throw new GenericException("Expression is not string type!");
        }
        return null;
    }

    @Override
    public String toString() {
        return "close(" + exp.toString() + ")";
    }
}

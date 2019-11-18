package com.ubb.domain.statements;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.exceptions.VariableAlreadyDeclaredException;
import com.ubb.domain.type.*;
import com.ubb.domain.value.Value;

public class VariableDeclarationStatement implements IStatement {
    String id;
    Type type;

    public VariableDeclarationStatement(String id, Type t) {
        this.id = id;
        this.type = t;
    }

    @Override
    public String toString() {
        return type.toString() + " " + id;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if (!symTable.containsKey(id)) {
            if (type instanceof BooleanType) {
                symTable.put(id, type.defaultValue());
            } else if (type instanceof IntegerType) {
                symTable.put(id, type.defaultValue());
            } else if (type instanceof StringType) {
                symTable.put(id, type.defaultValue());
            } else if (type instanceof RefType){
                symTable.put(id, type.defaultValue());
            }
        } else {
            throw new VariableAlreadyDeclaredException("Variable already declared!");
        }
        return state;
    }
}

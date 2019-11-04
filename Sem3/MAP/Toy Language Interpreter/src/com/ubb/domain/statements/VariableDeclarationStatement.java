package com.ubb.domain.statements;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.exceptions.VariableAlreadyDeclaredException;
import com.ubb.domain.type.BooleanType;
import com.ubb.domain.type.IntegerType;
import com.ubb.domain.type.Type;
import com.ubb.domain.value.BooleanValue;
import com.ubb.domain.value.IntegerValue;
import com.ubb.domain.value.Value;

public class VariableDeclarationStatement implements IStatement {
    String id;
    Type type;

    public VariableDeclarationStatement(String id, Type t){
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
        if(!symTable.containsKey(id)){
            if (type instanceof BooleanType){
                symTable.put(id, new BooleanValue(false));
            }else if(type instanceof IntegerType){
                symTable.put(id, new IntegerValue(0));
            }
        }else{
            throw new VariableAlreadyDeclaredException("Variable already declared!");
        }
        return state;
    }
}

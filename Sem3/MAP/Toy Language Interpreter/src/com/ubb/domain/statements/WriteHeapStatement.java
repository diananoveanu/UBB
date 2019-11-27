package com.ubb.domain.statements;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.expressions.Expression;
import com.ubb.domain.type.RefType;
import com.ubb.domain.value.RefValue;
import com.ubb.domain.value.Value;

public class WriteHeapStatement implements IStatement {
    private String varName;
    private Expression exp;

    public WriteHeapStatement(String varName, Expression exp) {
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        MyIDictionary<String, Value> symTabel = state.getSymTable();
        MyIDictionary<Integer, Value> heap = state.getHeap();
        if (symTabel.containsKey(varName)) {
            Value fromSymTable = symTabel.get(varName);
            if (fromSymTable.getType() instanceof RefType) {
                RefValue ref = (RefValue) fromSymTable;
                if (heap.containsKey(ref.getAddress())) {
                    Value evalExp = exp.evaluate(symTabel, heap);
                    if (evalExp.getType().equals(((RefType) ref.getType()).getInner())) {
                        heap.put(ref.getAddress(), evalExp);
                        return state;
                    } else {
                        //System.out.println("=========== " + evalExp.getType() + " ======= " + ref.getType());
                        throw new GenericException("Types don't match!");
                    }
                } else {
                    throw new GenericException(ref.getAddress() + " is not a valid address in heap!");
                }
            } else {
                throw new GenericException(varName + " is not of type Ref");
            }
        } else {
            throw new GenericException(varName + " is not declared");
        }
    }

    @Override
    public String toString() {
        return "wH(" + varName + ", " + exp.toString() + ")";
    }
}

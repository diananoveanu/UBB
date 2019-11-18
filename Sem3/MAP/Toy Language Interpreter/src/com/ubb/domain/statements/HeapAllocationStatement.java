package com.ubb.domain.statements;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.domain.ProgramState;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.expressions.Expression;
import com.ubb.domain.type.RefType;
import com.ubb.domain.value.RefValue;
import com.ubb.domain.value.Value;

import java.util.Set;

public class HeapAllocationStatement implements IStatement {
    private String varName;
    Expression expr;

    public HeapAllocationStatement(String varName, Expression expr) {
        this.varName = varName;
        this.expr = expr;
    }


    @Override
    public ProgramState execute(ProgramState state) throws GenericException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        Value exprValue = expr.evaluate(symTable, state.getHeap());
        if (symTable.containsKey(varName)) {
            Value fromSymTable = symTable.get(varName);
            if (fromSymTable.getType().equals(new RefType(exprValue.getType()))) {
                RefType ref = (RefType)fromSymTable.getType();
                if (exprValue.getType().equals(ref.getInner())) {
                    int dim = state.getHeap().size();
                    int key = 1;
                    if (dim > 0) {
                        int minKey = 1;
                        Set<Integer> keySet = state.getHeap().keySet();
                        for (Integer k : keySet) {
                            if (k > minKey) {
                                minKey = k;
                            }
                        }
                        key = minKey + 1;
                    }
                    state.getHeap().put(key, exprValue);
                    symTable.put(varName, new RefValue(exprValue.getType(), key));
                    return state;
                } else {
                    throw new GenericException("Types don't match!");
                }
            } else {
                throw new GenericException(varName + " is not a RefType");
            }
        } else {
            throw new GenericException(varName + " is not");
        }

    }

    @Override
    public String toString(){
        return "new(" + varName + ", " + expr.toString() + ")";
    }
}

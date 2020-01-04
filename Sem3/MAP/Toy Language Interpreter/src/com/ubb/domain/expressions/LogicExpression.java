package com.ubb.domain.expressions;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.type.BooleanType;
import com.ubb.domain.type.IntegerType;
import com.ubb.domain.type.Type;
import com.ubb.domain.value.BooleanValue;
import com.ubb.domain.value.Value;

public class LogicExpression implements Expression {
    Expression e1;
    Expression e2;
    int op; //1-^, 2-|

    public LogicExpression(Expression e1, Expression e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> tbl, MyIDictionary<Integer, Value> heap) throws GenericException {
        Value v1, v2;
        v1 = e1.evaluate(tbl, heap);
        if (v1.getType().equals(new BooleanType())) {
            v2 = e2.evaluate(tbl, heap);
            if (v2.getType().equals(new BooleanType())) {
                BooleanValue b1 = (BooleanValue) v1;
                BooleanValue b2 = (BooleanValue) v2;
                boolean n1, n2;
                n1 = b1.getValue();
                n2 = b2.getValue();
                if (op == 1) {
                    return new BooleanValue(n1 & n2);
                } else if (op == 2) {
                    return new BooleanValue(n1 | n2);
                }
            } else {
                throw new GenericException("Second operand is not a boolean!");
            }
        } else {
            throw new GenericException("First operand is not a boolean!");
        }
        return null;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws GenericException {
        Type typ1, typ2;
        typ1 = e1.typeCheck(typeEnv);
        typ2 = e2.typeCheck(typeEnv);
        if (typ1.equals(new BooleanType())) {
            if (typ2.equals(new BooleanType())) {
                return new BooleanType();
            } else {
                throw new GenericException("second operand is not a boolean");
            }
        } else {
            throw new GenericException("first operand is not a boolean");
        }
    }

    @Override
    public String toString() {
        String ope = "";
        if (op == 1) {
            ope = "^";
        } else if (op == 2) {
            ope = "|";
        }
        return e1.toString() + " " + ope + " " + e2.toString();
    }
}

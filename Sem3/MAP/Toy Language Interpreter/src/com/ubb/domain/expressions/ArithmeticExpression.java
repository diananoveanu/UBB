package com.ubb.domain.expressions;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.type.IntegerType;
import com.ubb.domain.type.Type;
import com.ubb.domain.value.IntegerValue;
import com.ubb.domain.value.Value;

import java.net.IDN;

public class ArithmeticExpression implements Expression {
    Expression e1;
    Expression e2;
    int op;//1-plus, 2-minus, 3-star, 4-divide

    public ArithmeticExpression(Expression e1, Expression e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public String toString() {
        String ope = "";
        if (op == 1) {
            ope = "+";
        } else if (op == 2) {
            ope = "-";
        } else if (op == 3) {
            ope = "*";
        } else {
            ope = "/";
        }
        return e1.toString() + " " + ope + " " + e2.toString();
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> tbl, MyIDictionary<Integer, Value> heap) throws GenericException {
        Value v1, v2;
        v1 = e1.evaluate(tbl, heap);
        if (v1.getType().equals(new IntegerType())) {
            v2 = e2.evaluate(tbl, heap);
            if (v2.getType().equals(new IntegerType())) {
                IntegerValue i1 = (IntegerValue) v1;
                IntegerValue i2 = (IntegerValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (op == 1) return new IntegerValue(n1 + n2);
                if (op == 2) return new IntegerValue(n1 - n2);
                if (op == 3) return new IntegerValue(n1 * n2);
                if (op == 4)
                    if (n2 == 0) throw new GenericException("division by zero");
                    else return new IntegerValue(n1 / n2);
            } else
                throw new GenericException("second operand is not an integer");
        } else
            throw new GenericException("first operand is not an integer");
        return null;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws GenericException {
        Type typ1, typ2;
        typ1 = e1.typeCheck(typeEnv);
        typ2 = e2.typeCheck(typeEnv);
        if (typ1.equals(new IntegerType())) {
            if (typ2.equals(new IntegerType())) {
                return new IntegerType();
            } else {
                throw new GenericException("second operand is not an integer");
            }
        } else {
            throw new GenericException("first operand is not an integer");
        }
    }
}
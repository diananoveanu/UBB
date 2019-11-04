package com.ubb.domain.expressions;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.type.IntegerType;
import com.ubb.domain.value.IntegerValue;
import com.ubb.domain.value.Value;

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
    public String toString(){
        String ope = "";
        if (op == 1) {
            ope = "+";
        }else if(op == 2){
            ope = "-";
        }else if(op == 3){
            ope = "*";
        }else{
            ope = "/";
        }
        return e1.toString() + " " + ope + " " + e2.toString();
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> tbl) throws GenericException {
        Value v1, v2;
        v1 = e1.evaluate(tbl);
        if (v1.getType().equals(new IntegerType())) {
            v2 = e2.evaluate(tbl);
            if (v2.getType().equals(new IntegerType())) {
                IntegerValue i1 = (IntegerValue) v1;
                IntegerValue i2 = (IntegerValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
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


    public Expression getE1() {
        return e1;
    }

    public void setE1(Expression e1) {
        this.e1 = e1;
    }

    public Expression getE2() {
        return e2;
    }

    public void setE2(Expression e2) {
        this.e2 = e2;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

}
package com.ubb.domain.expressions;

import com.ubb.adt.dictionary.MyIDictionary;
import com.ubb.domain.exceptions.GenericException;
import com.ubb.domain.type.BooleanType;
import com.ubb.domain.type.IntegerType;
import com.ubb.domain.type.Type;
import com.ubb.domain.value.BooleanValue;
import com.ubb.domain.value.IntegerValue;
import com.ubb.domain.value.Value;

public class RelationalExpression implements Expression {
    Expression exp1;
    Expression exp2;
    int rel; //1 <, //2 <=, 3 ==, 4 !=, 5 >, 6 >=

    public RelationalExpression(Expression ex1, Expression ex2, int rel) {
        this.exp1 = ex1;
        this.exp2 = ex2;
        this.rel = rel;
    }

    public Expression getExp1() {
        return exp1;
    }

    public Expression getExp2() {
        return exp2;
    }

    public int getRel() {
        return rel;
    }

    @Override
    public String toString() {
        String relOp = "";
        switch (rel) {
            case 1:
                relOp = "<";
                break;
            case 2:
                relOp = "<=";
                break;
            case 3:
                relOp = "==";
                break;
            case 4:
                relOp = "!=";
                break;
            case 5:
                relOp = ">";
                break;
            case 6:
                relOp = ">=";
                break;
            default:
                relOp = "";
                break;
        }

        return exp1.toString() + " " + relOp + " " + exp2.toString();
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> tbl, MyIDictionary<Integer, Value> heap) throws GenericException {
        Value v1, v2;
        v1 = exp1.evaluate(tbl, heap);
        if (v1.getType().equals(new IntegerType())) {
            v2 = exp2.evaluate(tbl, heap);
            if (v2.getType().equals(new IntegerType())) {
                IntegerValue i1 = (IntegerValue) v1;
                IntegerValue i2 = (IntegerValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                //1 <, //2 <=, 3 ==, 4 !=, 5 >, 6 >=
                if (rel == 1) return new BooleanValue(n1 < n2);
                if (rel == 2) return new BooleanValue(n1 <= n2);
                if (rel == 3) return new BooleanValue(n1 == n2);
                if (rel == 4) return new BooleanValue(n1 != n2);
                if (rel == 5) return new BooleanValue(n1 > n2);
                if (rel == 6) return new BooleanValue(n1 >= n2);
            } else
                throw new GenericException("second operand is not an integer");
        } else
            throw new GenericException("first operand is not an integer");
        return null;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws GenericException {
        Type typ1, typ2;
        typ1 = exp1.typeCheck(typeEnv);
        typ2 = exp2.typeCheck(typeEnv);
        if (typ1.equals(new IntegerType())) {
            if (typ2.equals(new IntegerType())) {
                return new BooleanType();
            } else {
                throw new GenericException("second operand is not an integer");
            }
        } else {
            throw new GenericException("first operand is not an integer");
        }
    }
}

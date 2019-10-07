package com.ubb.domain.expressions;

import com.ubb.adt.dict.MyIDictionary;

public class ArithmExp extends Exp {
    Exp e1;
    Exp e2;
    int op;

    public ArithmExp(Exp e1, Exp e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    Integer eval(MyIDictionary<String, Integer> tbl) {
        if (op == 1) return (e1.eval(tbl) + e2.eval(tbl));
        else return 0;
    }

    public Exp getE1() {
        return e1;
    }

    public void setE1(Exp e1) {
        this.e1 = e1;
    }

    public Exp getE2() {
        return e2;
    }

    public void setE2(Exp e2) {
        this.e2 = e2;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

}
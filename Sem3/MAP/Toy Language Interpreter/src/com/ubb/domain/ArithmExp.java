package com.ubb.domain;

import com.ubb.adt.MyIDictionary;

public class ArithmExp extends Exp {
    Exp e1;
    Exp e2;
    int op;

    int eval(MyIDictionary<String, Integer> tbl) {
        if (op == 1) return (e1.eval(tbl) + e2.eval(tbl));
    }
}

package com.ubb.domain;

import com.ubb.adt.MyIDictionary;

public class VarExp extends Exp {

    String id;

    int eval(MyIDictionary<String, Integer> tbl){
        return tbl.lookup(id);
    }
}

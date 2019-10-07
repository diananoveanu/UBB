package com.ubb.domain;

import com.ubb.adt.MyIDictionary;

public class ConstExp extends Exp {
    int number;

    int eval(MyIDictionary<String, Integer> tbl){
        return number;
    }
}

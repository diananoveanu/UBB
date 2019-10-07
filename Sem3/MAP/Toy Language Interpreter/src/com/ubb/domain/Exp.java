package com.ubb.domain;

import com.ubb.adt.MyIDictionary;

abstract class Exp {
    abstract int Eval(MyIDictionary<String, Integer> tbl);

}

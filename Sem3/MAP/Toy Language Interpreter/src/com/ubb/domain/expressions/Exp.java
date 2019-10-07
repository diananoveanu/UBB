package com.ubb.domain.expressions;

import com.ubb.adt.dict.MyIDictionary;

public abstract class Exp {
    abstract Integer eval(MyIDictionary<String, Integer> tbl);
}

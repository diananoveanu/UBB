package com.ubb.domain.expressions;

import com.ubb.adt.dict.MyIDictionary;
import com.ubb.domain.value.Value;

interface Exp {
    Value eval(MyIDictionary<String, Value> tbl);
}

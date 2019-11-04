package com.ubb.domain.value;

import com.ubb.domain.type.Type;
/*
A value Value in our language can be either an integer number (ConstNumber),
or boolean values like (ConstTrue) or (ConstFalse):
Value ::= Number (ConstNumber) | True (ConstTrue) | False (ConstFalse)
*/

public interface Value {
    Type getType();
}

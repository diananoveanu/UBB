package com.ubb.domain.type;

import com.ubb.domain.value.Value;

public interface Type {
    boolean equals(Object another);

    Value defaultValue();
}

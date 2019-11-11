//
// Created by Diana  on 11/6/19.
//

#include <string>
#include "Polynomial.h"

std::ostream &operator<<(std::ostream &os, const Polynomial &dt) {
    std::vector<int> number = dt.getPoly();
    for(int i = number.size() - 1; i>=0; i--){
        os << number[i];
    }
    return os;
}

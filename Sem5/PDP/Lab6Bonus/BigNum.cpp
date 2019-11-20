//
// Created by Diana on 11/13/2019.
//

#include <iostream>
#include "BigNum.h"

std::vector<int> BigNum::stringToNum(std::string &numString) {
    std::vector<int> number;
    for (int i = numString.size() - 1; i >= 0; i--) {
        number.push_back(numString[i] - '0');
    }
    return number;
}

std::ostream &operator<<(std::ostream &os, const BigNum &dt) {
    /**
     * override out operator
     */
    std::vector<int> number = dt.num;
    for (int i = number.size() - 1; i >= 0; i--) {
        os << number[i];
    }
    return os;
}

BigNum BigNum::operator*(const BigNum &other) {
    /**
     * Sequential multiplication for big numbers
     */
    int resDim = this->getNum().size() + other.getNum().size() - 1;
    std::vector<int> result(resDim);

    auto A = this->getNum();
    auto B = other.getNum();

    for (int i = 0; i < A.size(); i++) {
        for (int j = 0; j < B.size(); j++) {
            result[i + j] += A[i] * B[j];
        }
    }
    int T = 0;
    for (int i = 0; i < result.size(); i++) {
        T = (result[i] += T) / 10;
        result[i] %= 10;
    }
    if (T) { result.push_back(T); }

    std::string str = "";
    BigNum final = BigNum(str);
    final.setNum(result);
    return final;
}

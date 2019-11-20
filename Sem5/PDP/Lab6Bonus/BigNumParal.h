//
// Created by Diana on 11/13/2019.
//

#ifndef LAB1_CPP_BIGNUMPARAL_H
#define LAB1_CPP_BIGNUMPARAL_H

#include <vector>
#include "BigNum.h"

void multiplyBigNumParallel(std::vector<int> *a, std::vector<int> *b, std::vector<int> *result, int threadNum);

BigNum mulNumsParallel(BigNum a, BigNum b, int numThreads, double &execTime);

#endif //LAB1_CPP_BIGNUMPARAL_H

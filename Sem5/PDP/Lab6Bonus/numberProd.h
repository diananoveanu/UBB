//
// Created by Diana on 11/13/2019.
//

#ifndef LAB_NUMBERPROD_H
#define LAB_NUMBERPROD_H

#include <vector>
#include "BigNum.h"

using namespace std;

void numberProdParallel(vector<int> *a, vector<int> *b, vector<int> *result, int threads, bool debug);

BigNum karatsubaParallel(const BigNum &A, const BigNum &B, int threadNum, double &execTime);

std::vector<int> karatsubaSeq(std::vector<int> A, std::vector<int> B);

BigNum karatsubaSequential(const BigNum &A, const BigNum &B, double &execTime);

#endif //LAB_NUMBERPROD_H

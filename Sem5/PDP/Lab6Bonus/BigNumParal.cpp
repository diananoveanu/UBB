//
// Created by Diana on 11/13/2019.
//

#include "BigNumParal.h"
#include "BigNum.h"
#include <thread>
#include <iostream>


void thread_mul_func(std::vector<int> *a, std::vector<int> *b, std::vector<int> *result, int start, int stop) {
    /**
     * function to multiply a part of a number with another number
     */
    for (int i = start; i < stop; i++) {
        int carry = 0;
        for (int j = 0; j < a->size(); j++) {
            int prod = (*a)[j] * (*b)[i] + carry;
            carry = prod / 10;
            prod = prod % 10;

            (*result)[i + j] += prod;
        }
        if (carry) {
            (*result)[i + b->size()] = carry;
        }
    }
    int carry = 0;
    for (int &i : *result) {
        i += carry;
        carry = i / 10;
        i %= 10;
    }
}

void addBigNumsSequential(std::vector<int> A, std::vector<int> B, std::vector<int> &result) {
    int c = 0, sum = 0;
    for (int i = 0; i < A.size(); i++) {
        sum = A[i] + B[i] + c;
        result[i] = sum % 10;
        c = sum / 10;
    }
    if (c) {
        result.push_back(1);
    }
}

void multiplyBigNumParallel(std::vector<int> *a, std::vector<int> *b, std::vector<int> *result, int threadNum) {
    int perThread = b->size() / threadNum;
    int rem = b->size() % threadNum;

    int start = 0, stop = 0;
    std::vector<std::thread> threads(threadNum);
    std::vector<int> threadResults[threadNum];
    for (int i = 0; i < threadNum; i++) {
        threadResults[i] = std::vector<int>(a->size() * 2, 0);
    }

    for (int th_index = 0; th_index < threadNum; th_index++) {
        stop = start + perThread;
        if (rem) {
            stop += 1;
            rem -= 1;
        }

        threads[th_index] = std::thread(thread_mul_func, a, b, &threadResults[th_index], start, stop);
        start = stop;
    }

    for (int th_index = 0; th_index < threadNum; th_index++) {
        threads[th_index].join();
    }

    for (int th_index = 0; th_index < threadNum; th_index++) {
        addBigNumsSequential(*result, threadResults[th_index], *result);
    }
}

BigNum mulNumsParallel(BigNum a, BigNum b, int numThreads, double &execTime) {
    auto A = a.getNum();
    auto B = b.getNum();
    std::vector<int> rez(2 * A.size(), 0);
    auto start = std::chrono::steady_clock::now();
    multiplyBigNumParallel(&A, &B, &rez, numThreads);
    auto end = std::chrono::steady_clock::now();
    auto time = std::chrono::duration<double, std::milli>(end - start).count();
    execTime = time * 0.001;
    return BigNum(rez);
}

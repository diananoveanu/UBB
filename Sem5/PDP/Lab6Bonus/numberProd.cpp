//
// Created by Diana on 11/13/2019.
//

#include <thread>
#include "numberProd.h"
#include <mutex>
#include "numberDiff.h"

//----Karatsuba approach following
/**
 *      Computes the product sequentially.
 * @param a : first operand of product
 * @param startA : starting interval in a
 * @param stopA : stopping point in a
 * @param b : second operand of product
 * @param startB : starting interval in b
 * @param stopB : stopping point in b
 * @param result : pointer to result pointer
 */
void numberProdSequentialAux(vector<int> *a, int startA, int stopA, vector<int> *b, int startB, int stopB,
                             vector<int> **result) {

    vector<int> *res = new vector<int>((stopA - startA) * 2, 0);

    for (int i = startB; i < stopB; i++) {
        int carry = 0;
        for (int j = startA; j < stopA; j++) {
            int prod = (*a)[j] * (*b)[i] + carry;
            carry = prod / 10;
            prod = prod % 10;

            (*res)[i + j - startA - startB] += prod;
        }
        if (carry) {
            (*res)[i + stopA - startA - startB] = carry;
        }
    }
    int carry = 0;
    for (int i = 0; i < res->size(); i++) {
        (*res)[i] += carry;
        carry = (*res)[i] / 10;
        (*res)[i] %= 10;
    }

    *result = res;
}

vector<int> *firstPlusSecondPart(vector<int> *arr, int start, int stop) {

    int middle = (stop + start) / 2;
    vector<int> *result = new vector<int>(max(middle - start, stop - middle), 0);
    for (int i = start; i < middle; i++) {
        (*result)[i - start] = (*arr)[i];
    }
    for (int i = middle; i < stop; i++) {
        (*result)[i - middle] += (*arr)[i];
    }
    int carry = 0;
    for (int i = 0; i < result->size(); i++) {
        (*result)[i] += carry;
        carry = (*result)[i] / 10;
        (*result)[i] = (*result)[i] % 10;
    }
    if (carry) {
        (*result).push_back(carry);
    }
    return result;
}


void numberProdKaratsubaAux(vector<int> *a, int startA, int stopA, vector<int> *b, int startB, int stopB,
                            vector<int> **result, int powerOf10, int threads) {

    //if the lengths are short enough, or no threads available to use,apply the sequential method
    if (stopA - startA < 5 || stopB - stopA < 5 || threads <= 0) {
        numberProdSequentialAux(a, startA, stopA, b, startB, stopB, result);
        return;
    }

    //else: going on with the karatsuba approach, splitting the problem into independent subproblems
    //step0: define how many threads are allocated for each of the three steps and whats the cut point in the arrays
    int middleA = (stopA + startA) / 2;
    int middleB = (stopB + startB) / 2;
    int threadsS1 = threads / 3;
    int threadsS2 = threads / 3;
    int threadsS3 = threads / 3;
    threads = threads % 3;

    thread *thS1 = nullptr, *thS2 = nullptr, *thS3 = nullptr;

    if (threads == 1) {
        threadsS2 += 1;
    } else if (threads == 2) {
        threadsS1 += 1;
        threadsS2 += 1;
    }

    //step1: add A0 * B0 , with 2* (n/2) offset
    vector<int> *resS1 = new vector<int>((stopA - middleA) * 2, 0);
    if (threadsS1 > 0) {
        thS1 = new thread(numberProdKaratsubaAux, a, middleA, stopA, b, middleB, stopB, &resS1, 0, threadsS3 - 1);
    } else {
        numberProdKaratsubaAux(a, middleA, stopA, b, middleB, stopB, &resS1, 0, threadsS3 - 1);
    }

    //step3: add A1 * B1 , with no offset
    vector<int> *resS3 = new vector<int>((middleA - startA) * 2, 0);
    if (threadsS3 > 0) {
        thS3 = new thread(numberProdKaratsubaAux, a, startA, middleA, b, startB, middleB, &resS3, 0, threadsS1 - 1);
    } else {
        numberProdKaratsubaAux(a, startA, middleA, b, startB, middleB, &resS3, 0, threadsS1 - 1);
    }

    //step2:
    //A1 + A0
    vector<int> *a1plus0 = firstPlusSecondPart(a, startA, stopA);
    //B1 + B0
    vector<int> *b1plus0 = firstPlusSecondPart(b, startB, stopB);
    //(A1 + A0) * (B1 + B0)
    vector<int> *prodS2 = new vector<int>(2 * max(a1plus0->size(), b1plus0->size()), 0);
    if (threadsS2 > 0) {
        thS2 = new thread(numberProdKaratsubaAux, a1plus0, 0, a1plus0->size(), b1plus0, 0, b1plus0->size(), &prodS2, 0,
                          threadsS2 - 1);
    } else {
        int size1 = a1plus0->size();
        int size2 = b1plus0->size();
        numberProdKaratsubaAux(a1plus0, 0, size1, b1plus0, 0, size2, &prodS2, 0, threadsS2 - 1);
    }
    //res = prodS2 - (A0*B0 + A1*B1)
    //by this time we need the results from s1 and s3
    if (thS1) {
        thS1->join();
    }
    if (thS3) {
        thS3->join();
    }

    numberDiff(prodS2, resS1);
    numberDiff(prodS2, resS3);

    //sum the individual results of each step into the result array
    vector<int> *res = new vector<int>((stopA - startA) * 2, 0);

    //sum step1

    for (int i = middleA * 2; i - middleA * 2 < resS1->size(); i++) {
        (*res)[i] += (*resS1)[i - middleA * 2];
    }
    delete resS1;

    //sum step2
    for (int i = middleA; i - middleA < prodS2->size(); i++) {
        (*res)[i] += (*prodS2)[i - middleA];
    }
    delete prodS2;
    //sum step3
    for (int i = 0; i < resS3->size(); i++) {
        (*res)[i] += (*resS3)[i];
    }
    delete resS3;

    *result = res;
}


/**
 * Multiplying two numbers using the Karatsuba approach. The method calls a auxiliary method with more parameters,
 * then copies the result.
 * @param a
 * @param b
 * @param result
 * @param threads
 * @param debug
 */
void numberProdKaratsuba(vector<int> *a, vector<int> *b, vector<int> *result, int threads, bool debug) {
    vector<int> *intermResult = new vector<int>(a->size() * 2, 0);

    numberProdKaratsubaAux(a, 0, a->size(), b, 0, b->size(), &intermResult, 0, threads);
    int carry = 0;
    for (int i = 0; i < intermResult->size(); i++) {
        (*result)[i] = (*intermResult)[i] + carry;
        carry = (*intermResult)[i] / 10;
        (*intermResult)[i] = (*intermResult)[i] % 10;
    }
}

void numberProdParallel(vector<int> *a, vector<int> *b, vector<int> *result, int threads, bool debug) {
    numberProdKaratsuba(a, b, result, threads, debug);
}


std::vector<int> karatsubaSeq(std::vector<int> A, std::vector<int> B) {
    std::vector<int> product = std::vector<int>(2 * B.size(), 0);

    if (B.size() == 1) {
        product[0] = A[0] * B[0];
        return product;
    }

    int halfSize = A.size() / 2;

    //Arrays for halved factors
    auto aLow = std::vector<int>(halfSize, 0);
    auto aHigh = std::vector<int>(halfSize, 0);
    auto bLow = std::vector<int>(halfSize, 0);
    auto bHigh = std::vector<int>(halfSize, 0);

    auto aLowHigh = std::vector<int>(halfSize, 0);
    auto bLowHigh = std::vector<int>(halfSize, 0);
    //A - multiplicand, B - multiplier
    //Fill low and high arrays
    for (int i = 0; i < halfSize; ++i) {
        aLow[i] = A[i];
        aHigh[i] = A[halfSize + i];
        aLowHigh[i] = aHigh[i] + aLow[i];

        bLow[i] = B[i];
        bHigh[i] = B[halfSize + i];
        bLowHigh[i] = bHigh[i] + bLow[i];
    }

    //Recursively call method on smaller arrays
    auto productLow = karatsubaSeq(aLow, bLow);
    auto productHigh = karatsubaSeq(aHigh, bHigh);
    auto productLowHigh = karatsubaSeq(aLowHigh, bLowHigh);

    //Construct middle portion of the product
    auto productMiddle = std::vector<int>(A.size(), 0);
    for (int i = 0; i < A.size(); ++i) {
        productMiddle[i] = productLowHigh[i] - productLow[i] - productHigh[i];
    }

    //Assemble the product from the low, middle and high parts
    int carry1 = 0;
    int carry2 = 0;
    int carry3 = 0;
    int midOffset = A.size() / 2;
    for (int i = 0; i < A.size(); ++i) {
        auto prod = productLow[i] + carry1;
        carry1 = prod / 10;
        prod = prod % 10;
        product[i] += prod;

        prod = productHigh[i] + carry2;
        carry2 = prod / 10;
        prod = prod % 10;
        product[i + A.size()] += prod;

        prod = productMiddle[i] + carry3;
        carry3 = prod / 10;
        prod = prod % 10;
        product[i + midOffset] += prod;
    }

    return product;
}

BigNum karatsubaParallel(const BigNum &A, const BigNum &B, int threadNum, double &execTime) {
    vector<int> result = vector<int>(2 * A.getNum().size() - 1, 0);
    auto a = A.getNum();
    auto b = A.getNum();
    auto start = std::chrono::steady_clock::now();
    numberProdParallel(&a, &b, &result, threadNum, false);
    auto stop = std::chrono::steady_clock::now();
    auto diff = stop - start;
    auto time = std::chrono::duration<double, std::milli>(diff).count();
    execTime = time * 0.001;
    return BigNum(result);
}

BigNum karatsubaSequential(const BigNum &A, const BigNum &B, double &execTime) {
    auto a = A.getNum();
    auto b = B.getNum();
    auto start = std::chrono::steady_clock::now();
    auto rez = karatsubaSeq(a, b);
    auto stop = std::chrono::steady_clock::now();
    auto diff = stop - start;
    auto time = std::chrono::duration<double, std::milli>(diff).count();
    execTime = time * 0.001;
    return BigNum(rez);
}

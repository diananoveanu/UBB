//
// Created by Diana  on 2/4/20.
//

#include <iostream>
#include <vector>
#include <math.h>
#include <thread>

using namespace std;

void getPrimesLinearly(vector<int> &primes, int n) {
    for (int x = 2; x <= n; x++) {
        bool isPrime = true;
        for (int y = 2; y <= x / 2; y++) {
            if (x % y == 0) {
                isPrime = false;
                break;
            }
        }
        if (isPrime) {
            primes.push_back(x);
        }
    }
}

void checkPrimes(int start, int stop, int sqN, std::vector<int> *firstPrimes, std::vector<bool> *primes) {
    for (int j = start + sqN; j < stop + sqN; j++) {
        bool is_prime = true;
        for (const auto &prime: *firstPrimes) {
            if (j % prime == 0) {
                is_prime = false;
                break;
            }
        }
        if (is_prime) {
            (*primes)[j] = true;
        }
    }
}

void getPrimesThreads(vector<bool> *primes, int n, int T) {
    int sqN = sqrt(n);
    vector<int> firstPrimes;
    getPrimesLinearly(firstPrimes, sqN);
    for (const auto &elem : firstPrimes) {
        (*primes)[elem] = true;
    }
    std::cout << '\n';
    vector<thread> threads(T);
    int i;
    int newN = n - sqN;
    int perThread = newN / T;
    int rem = newN % T;
    int start, stop;
    start = stop = 0;
    for (i = 0; i < T; i++) {
        stop = start + perThread + (rem > 0);
        if (rem) {
            rem--;
        }
        std::thread th(checkPrimes, start, stop, sqN, &firstPrimes, primes);
        threads[i] = std::move(th);
        start = stop;
    }
    for (i = 0; i < T; i++) {
        threads[i].join();
    }
}

int main() {
    int n = 100;
    vector<bool> primes(n, false);
    getPrimesThreads(&primes, n, 4);

    for (int i = 0; i < n; i++) {
        if (primes[i]) {
            std::cout << i << ' ';
        }
    }
    return 0;
}
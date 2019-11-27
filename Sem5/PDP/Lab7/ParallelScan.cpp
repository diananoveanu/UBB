//
// Created by Diana  on 11/23/19.
//

#include "ParallelScan.h"
#include <cmath>
#include <thread>

std::mutex mtxUp;
std::mutex mtxDown;

int power(int a, int n) {
    if (n == 0) {
        return 1;
    }
    if (n % 2 == 0) {
        return power(a, n / 2) * power(a, n / 2);
    } else {
        return a * power(a, n - 1);
    }
}

void threadFuncUpSweep(std::vector<int> *a, int ind) {
    int step = power(2, ind + 1);
    int dim = (*a).size();
    for (int k = 0; k < dim; k += step) {
        if (k + step * 2 - 1 < (*a).size()) {
            mtxUp.lock();
            (*a)[k + step - 1] = (*a)[k + (step >> 1) - 1] + (*a)[k + step - 1];
            mtxUp.unlock();
        }
    }
}

void upSweep(std::vector<int> *a) {
    std::vector<std::thread *> threads((int) log2((*a).size()) - 1);
    int dim = (int) log2((*a).size()) - 1;
    for (int d = 0; d < dim; d++) {
//        mtxUp.lock();
        auto *th = new std::thread(threadFuncUpSweep, a, d);
//        mtxUp.unlock();
        threads[d] = th;
    }

    for (auto &x : threads) {
        x->join();
        delete x;
    }

}

void threadFuncDownSweep(std::vector<int> *a, int d) {
    int step = power(2, d + 1);
    int dim = (*a).size();
    for (int k = 0; k < dim; k += step) {
        mtxDown.lock();
        int t = (*a)[k + (step >> 1) - 1];
        (*a)[k + (step >> 1) - 1] = (*a)[k + step - 1];
        (*a)[k + step - 1] = t + (*a)[k + step - 1];
        mtxDown.unlock();
    }
}


void downSweep(std::vector<int> *a) {
    int dim = (int) log2((*a).size()) - 1;
    std::vector<std::thread *> threads(dim);
    (*a)[(*a).size() - 1] = 0;
    for (int d = dim; d >= 0; d--) {
//        mtxDown.lock();
        auto *th = new std::thread(threadFuncDownSweep, a, d);
//        mtxDown.unlock();
        threads[d] = th;
    }

    for (auto &x : threads) {
        x->join();
        delete x;
    }
}

std::vector<int> parallelScan(std::vector<int> &a) {
    int last = a[a.size() - 1];
    upSweep(&a);
    downSweep(&a);
    a.push_back(a[a.size() - 1] + last);
    a.erase(a.begin());
    return a;
}

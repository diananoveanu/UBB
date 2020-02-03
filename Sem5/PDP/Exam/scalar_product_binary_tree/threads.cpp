//
// Created by Diana on 2/3/20.
//

#include <iostream>
#include <thread>
#include <vector>

using namespace std;

int leftNode(int index) {
    return (2 * index + 1);
}

int rightNode(int index) {
    return (2 * index + 2);
}

bool hasLeftNode(int n, int index) {
    int node = leftNode(index);
    return (node >= 0 && node < n);
}

bool hasRightNode(int n, int index) {
    int node = rightNode(index);
    return (node >= 0 && node < n);
}


int scalarProduct(vector<int> a, vector<int> b, int T) {

    vector<int> sums(a.size(), 0);
    vector<thread> threads;
    threads.resize(T);

    int start = 0, stop = 0;
    int step = a.size() / T;
    int mod = a.size() % T;

    for (int i = T - 1; i >= 0; i--) {
        stop = start + step;
        if (mod > 0) {
            mod--;
            stop++;
        }
        threads[i] = thread([&, i, start, stop]() {
            for (int k = start; k < stop; k++) {
                sums[i] += a[k] * b[k];
            }

            if (hasLeftNode(T, i)) {
                threads[leftNode(i)].join();
                sums[i] += sums[leftNode(i)];
            }

            if (hasRightNode(T, i)) {
                threads[rightNode(i)].join();
                sums[i] += sums[rightNode(i)];
            }
        });
        start = stop;
    }

    threads[0].join();
    return sums[0];
}

int main() {
    cout << scalarProduct({1, 2, 3, 4}, {2, 3, 4, 5}, 4);
    return 0;
}
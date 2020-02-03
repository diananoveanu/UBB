//
// Created by Diana on 2/3/20.
//

#include <iostream>
#include <thread>
#include <vector>

using namespace std;

int scalarProduct(vector<int> a, vector<int> b, int T) {
    vector<int> sums(a.size(), 0);
    vector<thread> threads;
    threads.resize(T);
    int final_sum = 0, start = 0, stop = 0;
    int step = a.size() / T;
    int mod = a.size() % T;

    for (int i = 0; i < T; i++) {
        stop = start + step;
        if (mod > 0) {
            mod--;
            stop++;
        }
        threads[i] = thread([&, i, start, stop]() {
            for (int k = start; k < stop; k++) {
                sums[i] += a[k] * b[k];
            }
        });
        start = stop;
    }

    for (int i = 0; i < T; i++) {
        threads[i].join();
    }

    for (int i = 0; i < a.size(); i++) {
        final_sum += sums[i];
    }
    return final_sum;
}

int main() {
    cout << scalarProduct({1, 2, 3, 4}, {2, 3, 4, 5}, 2);
    return 0;
}
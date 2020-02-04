//
// Created by Diana on 2/3/20.
//

#include <iostream>
#include <thread>
#include <vector>

using namespace std;

void multiply(vector<int> a, vector<int> b, vector<int> &r, int T) {

    int start = 0, stop = 0;
    int step = a.size() / T;
    int mod = a.size() % T;
    if (T == 1) {
        for (int i = 0; i < a.size(); i++)
            for (int j = 0; j < a.size(); j++)
                r[i + j] += a[i] * b[j];
    } else {
        vector<thread> threads(T);
        for (int k = 0; k < T; k++) {
            stop = start + step;
            if (mod > 0) {
                mod--;
                stop++;
            }
            threads[k] = thread([&, k, start, stop]() {
                for (int i = start; i < stop; i++)
                    for (int j = 0; j < b.size(); j++)
                        r[i + j] += a[i] * b[j];
            });
            start = stop;
        }
        for (int i = 0; i < T; i++)
            threads[i].join();
    }
}

int main() {
    vector<int> a;
    vector<int> b;
    vector<int> result(5, 0);

    multiply({1, 2, 3}, {1, 2, 3}, result, 3);

    for (auto i : result)
        cout << i << " ";
    cout << "\n";

    return 0;
}
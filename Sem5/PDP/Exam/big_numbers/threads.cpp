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
                for (int i = start; i < stop; i++) {
                    int carry = 0;
                    for (int j = 0; j < b.size(); j++) {
                        int prod = a[i] * b[j] + carry;
                        carry = prod / 10;
                        prod = prod % 10;
                        r[i + j] += prod;
                    }
                    if(carry) {
                        r[i + b.size()] = carry;
                    }
                }
                int carry = 0;
                carry = 0;
                for(int i = r.size() - 1; i>=0; i--){
                    r[i] += carry;
                    carry = r[i] / 10;
                    r[i] %= 10;

                }

                while(carry > 0){
                    r.push_back(carry % 10);
                    carry /= 10;
                }
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

    multiply({1, 9, 9}, {1, 9, 9}, result, 3);

    for (auto i : result)
        cout << i << " ";
    cout << "\n";

    return 0;
}
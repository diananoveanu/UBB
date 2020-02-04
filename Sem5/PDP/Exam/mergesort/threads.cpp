#include<iostream>
#include<thread>
#include<vector>

using namespace std;

vector<int> unite(vector<int> a, vector<int> b) {
    vector<int> result;

    int n = 0;
    int m = 0;

    while (n < a.size() && m < b.size()) {
        if (a[n] <= b[m]) {
            result.push_back(a[n++]);
        } else {
            result.push_back(b[m++]);
        }
    }

    while (n < a.size()) {
        result.push_back(a[n++]);
    }

    while (m < b.size()) {
        result.push_back(b[m++]);
    }

    return result;
}

vector<int> mergeSort(vector<int> v, int T) {
    if (v.size() <= 1) {
        return v;
    }

    int halfSize = v.size() / 2;
    vector<int> lowHalf(v.begin(), v.begin() + halfSize);
    vector<int> highHalf(v.begin() + halfSize, v.end());

    if (T == 1) {
        return unite(mergeSort(lowHalf, T), mergeSort(highHalf, T));
    } else {
        thread t1([&]() {
            lowHalf = mergeSort(lowHalf, T / 2);
        });

        thread t2([&]() {
            highHalf = mergeSort(highHalf, T / 2 + 1);
        });

        t1.join();
        t2.join();

        return unite(lowHalf, highHalf);
    }
}

int main() {

    vector<int> result = mergeSort({9, 8, 7, 6, 5, 4, 3, 2, 1}, 4);

    for (int i = 0; i < result.size(); i++) {
        cout << result[i] << " ";
    }

}
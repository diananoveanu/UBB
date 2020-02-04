#include <iostream>
#include <thread>
#include <vector>

using namespace std;

vector<int> unite(vector<int> a, int pivot, vector<int> b) {
    vector<int> result(a);

    result.push_back(pivot);

    result.insert(result.end(), b.begin(), b.end());
    return result;
}

vector<int> quickSort(vector<int> v, int T) {
    if (v.size() <= 1) {
        return v;
    }

    vector<int> left, right;

    int pivot = v[v.size() / 2];
    v.erase(v.begin() + v.size() / 2);

    for (int i = 0; i < v.size(); i++) {
        if (v[i] < pivot) {
            left.push_back(v[i]);
        }
        else {
            right.push_back(v[i]);
        }
    }

    if (T == 1) {
        return unite(quickSort(left, T), pivot, quickSort(right, T));
    }
    else {
        thread t1([&]() {
            left = quickSort(left, T / 2);
        });
        thread t2([&]() {
            right = quickSort(right, T - T / 2);
        });

        t1.join();
        t2.join();

        return unite(left, pivot, right);
    }
}

int main() {

    vector<int> result = quickSort({ 9, 8, 7, 6, 4,54, 3, 2, 1 }, 4);

    for (int i = 0; i < result.size(); i++) {
        cout << result[i] << " ";
    }

    return 0;
}
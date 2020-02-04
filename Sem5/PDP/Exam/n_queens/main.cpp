#include <iostream>
#include <thread>
#include <vector>
#include <atomic>

using namespace std;

atomic<int> cnt;

bool check(vector<int>  &v, int col, int val) {
    return (col >= 0 && col < v.size() && v[col] == val);
}

bool isSolution(vector<int> v) {
    for (int col = 0; col < v.size(); col++) {
        int row = v[col];
        for (int k = 1; k < v.size(); k++) {
            if (check(v, col - k, row - k) ||  // up-left
                check(v, col - k, row) ||      // left
                check(v, col - k, row + k) ||  // down-left
                check(v, col + k, row + k) ||  // down-right
                check(v, col + k, row) ||      // right
                check(v, col + k, row - k))    // up-right
            {
                return false;
            }
        }
    }
    return true;
}

void back(vector<int> sol, int n, int T) {

    if (sol.size() == n) {
        if (isSolution(sol)) {
            cnt++;
        }
        return;
    }

    if (T == 1) {
        for (int i = 0; i < n; i++) {
            sol.push_back(i);
            back(sol, n, T);
            sol.pop_back();
        }
    }
    else {
        vector<int> aux(sol);
        thread t([&]() {
            for (int i = 0; i < n; i += 2) {
                aux.push_back(i); ;
                back(aux, n, T / 2);
                aux.pop_back();
            }
        });

        for (int i = 1; i < n; i += 2) {
            sol.push_back(i);
            back(sol, n, T - T / 2);
            sol.pop_back();
        }

        t.join();
    }
}

int main() {

    back(vector<int>(), 8, 10);
    cout << cnt;

    return 0;
}
#include <iostream>
#include <vector>
#include <thread>
#include <atomic>

using namespace std;

atomic <int> cnt;

bool predicate(vector<int> configuration)
{
    return configuration[0] % 2 == 0;

}

bool contains(vector <int> v, int n) {
    for (auto it : v) {
        if (it == n) {
            return true;
        }
    }
    return false;
}

inline void backtracking(vector <int> configuration, int n, int pos, int T) {

    if (pos == n) {
        if (predicate(configuration)) {
            for (auto it : configuration)
                cout << it << " ";
            cout << "\n";
            cnt++;
        }

        return;
    }

    if (T == 1) {
        for (int i = 0; i < n; ++i) {
            if (contains(configuration, i)) continue;
            configuration.push_back(i);
            backtracking(configuration, n, pos + 1, T);
            configuration.pop_back();
        }
    }
    else {
        thread t([&]() {
            vector <int> newPath(configuration);
            for (int i = 0; i < n; i += 2) {
                if (contains(configuration, i)) continue;
                newPath.push_back(i);
                backtracking(newPath, n, pos + 1, T / 2);
                newPath.pop_back();
            }
        });
        vector <int> aux(configuration);
        for (int i = 1; i < n; i += 2) {
            if (contains(configuration, i)) continue;
            aux.push_back(i);
            backtracking(aux, n, pos + 1, T - T / 2);
            aux.pop_back();
        }
        t.join();
    }
}

int main() {
    backtracking(vector<int>(), 3, 0, 2);
    cout << cnt << "\n";
}
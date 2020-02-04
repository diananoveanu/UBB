#include <iostream>
#include <fstream>
#include <vector>
#include <thread>
#include <atomic>

using namespace std;

atomic <int> cnt;

bool predicate(vector<int> configuration)
{
    return configuration[0] % 2 == 0;

}


inline void backtracking(vector <int> configuration, int n, int pos, int T) {


    if (configuration.size() && predicate(configuration)) {
        for (auto it : configuration)
            cout << it << " ";
        cout << "\n";
        cnt++;
    }else{
        //if empty sets verifies the property then
        //cnt ++;
    }


    if (T == 1) {
        for (int i = pos; i < n; ++i) {

            configuration.push_back(i);
            backtracking(configuration, n, i + 1, T);
            configuration.pop_back();
        }
    }
    else {
        thread t([&]() {
            vector <int> newPath(configuration);
            for (int i = pos; i < n; i += 2) {
                newPath.push_back(i);
                backtracking(newPath, n, i + 1, T / 2);
                newPath.pop_back();
            }
        });
        vector <int> aux(configuration);
        for (int i = pos + 1; i < n; i += 2) {
            aux.push_back(i);
            backtracking(aux, n, i + 1, T - T / 2);
            aux.pop_back();
        }
        t.join();
    }
}

int main() {
    backtracking(vector<int>(), 5, 0, 3);
    cout << cnt << "\n";
}
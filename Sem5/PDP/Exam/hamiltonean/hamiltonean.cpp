#include <iostream>
#include <fstream>
#include <vector>
#include <thread>

using namespace std;

const int V = 5;
bool solved = false;

bool isSafe(int v, bool graph[V][V], vector<int> path, int pos)
{
    /* Check if this vertex is an adjacent vertex of the previously
    added vertex. */
    if (graph[path[pos - 1]][v] == 0)
        return false;

    /* Check if the vertex has already been included.
    This step can be optimized by creating an array of size V */
    for (int i = 0; i < pos; i++)
        if (path[i] == v)
            return false;

    return true;
}

inline void hamCycleUtil(bool graph[V][V], vector <int> path, int pos, int T) {
    if (solved)
        return;

    if (pos == V) {
        if (graph[path[pos - 1]][path[0]] == 1)
        {
            solved = true;
            for (auto it : path) {
                cerr << it << ' ';
            }
            cerr << '\n';
        }
        return;
    }

    if (T == 1) {
        for (int i = 0; i < V; ++i) {
            if (isSafe(i, graph, path, pos))
            {
                path.push_back(i);
                hamCycleUtil(graph, path, pos + 1, T);
                path.pop_back();
            }
        }
    }
    else {
        thread t([&]() {
            vector <int> newPath(path);
            for (int i = 0; i < V; i += 2) {
                if (isSafe(i, graph, path, pos))
                {
                    newPath.push_back(i);
                    hamCycleUtil(graph, newPath, pos + 1, T / 2);
                    newPath.pop_back();
                }
            }
        });
        vector <int> aux(path);
        for (int i = 1; i < V; i += 2) {
            if (isSafe(i, graph, path, pos))
            {
                aux.push_back(i);
                hamCycleUtil(graph, aux, pos + 1, T - T / 2);
                aux.pop_back();
            }
        }
        t.join();
    }
}

int main() {
    bool graph1[V][V] = { { 0, 1, 0, 1, 0 },
                          { 1, 0, 1, 1, 1 },
                          { 0, 1, 0, 0, 1 },
                          { 1, 1, 0, 0, 1 },
                          { 0, 1, 1, 1, 0 },
    };

    vector<int> path;
    path.push_back(0);
    hamCycleUtil(graph1, path, 1, 4);
}
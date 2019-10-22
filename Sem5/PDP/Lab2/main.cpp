////
//// Created by Diana  on 10/20/19.
////

#include <iostream>
#include <ctime>
#include <chrono>
#include <vector>
#include <thread>

using namespace std;
#define MAX_NUM 10

vector<vector<int>> createMatrix(int n, bool empty) {
    vector<vector<int>> matrix;
    for (int i = 0; i < n; i++) {
        vector<int> line(n);
        for (int j = 0; j < n; j++) {
            if (!empty) {
                line[j] = rand() % MAX_NUM;
            } else {
                line[j] = 0;
            }
        }
        matrix.push_back(line);
    }

    return matrix;
}

void printMatrix(const vector<vector<int>> &matrix) {
    for (const auto &line : matrix) {
        for (const auto &elem : line) {
            cout << elem << ' ';
        }
        cout << '\n';
    }
}

void matrixMulParallel(int tNum, vector<vector<int>> *A, vector<vector<int>> *B,
                       vector<vector<int>> *C, int n, int step) {

    int line = tNum / n;
    int col = tNum % n;

    while (line < n) {
        int sum = 0;
        for (int j = 0; j < n; j++) {
            sum += (*A)[line][j] * (*B)[j][col];
        }

        (*C)[line][col] = sum;
        tNum += step;
        line = tNum / n;
        col = tNum % n;
    }
}

void matrixMulSequential(vector<vector<int>> &A, vector<vector<int>> &B,
                         vector<vector<int>> &C, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            int sum = 0;
            for (int k = 0; k < n; k++) {
                sum += A[i][k] * B[k][j];
            }
            C[i][j] = sum;
        }
    }
}

void matrixSumSquential(vector<vector<int>> &A, vector<vector<int>> &B,
                        vector<vector<int>> &C, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            C[i][j] = A[i][j] + B[i][j];
        }
    }
}

void matrixSumParallel(int ind, vector<vector<int>> *A, vector<vector<int>> *B,
                       vector<vector<int>> *C, int n, int step) {
    int tNum = ind;
    int line = tNum / n;
    int col = tNum % n;

    while (line < n) {
        (*C)[line][col] = (*A)[line][col] + (*B)[line][col];
        tNum += step;
        line = tNum / n;
        col = tNum % n;
    }
}

int main() {
    srand(time(nullptr)); // set seed
    int n, numThreads;
    cout << "Matrix dimension:";
    cin >> n;
    cout << '\n';
    cout << "Number of threads:";
    cin >> numThreads;
    // Init matrices
    auto A = createMatrix(n, false);
    auto B = createMatrix(n, false);
    auto C = createMatrix(n, true);

    // Parallel multiplication
    vector<thread> threads;
    auto start = chrono::steady_clock::now(); // start time
    for (int i = 0; i < numThreads; i++) {
        thread th(matrixMulParallel, i, &A, &B, &C, n, numThreads);
        threads.push_back(move(th)); // only way to not get a copy of the thread is to use 'move'
    }

    for (auto &x : threads) {
        x.join();
    }

    auto end = chrono::steady_clock::now(); // end time
    auto diff = end - start;
    cout << "Parallel time for matrix multiplication: " << chrono::duration<double, milli>(diff).count() * 0.001 << " s"
         << endl; // convert to milliseconds from double

    printMatrix(A);
    cout << '\n';
    printMatrix(B);
    cout << '\n';
    printMatrix(C);
    cout << '\n';

    // Sequential multiplication
    C = createMatrix(n, true);
    auto start1 = chrono::steady_clock::now();
    matrixMulSequential(A, B, C, n);
    auto end1 = chrono::steady_clock::now();
    auto diff1 = end1 - start1;
    cout << "Sequential time for matrix mul: " << chrono::duration<double, milli>(diff1).count() * 0.001 << " s"
         << endl;

    // Parallel sum
    C = createMatrix(n, true);
    vector<thread> threadSum;
    auto start2 = chrono::steady_clock::now();
    for (int i = 0; i < numThreads; i++) {
        thread th(matrixSumParallel, i, &A, &B, &C, n, numThreads);
        threadSum.push_back(move(th));
    }

    for (auto &x : threadSum) {
        x.join();
    }

    auto end2 = chrono::steady_clock::now();
    auto diff2 = end2 - start2;
    cout << "Parallel time for matrix sum: " << chrono::duration<double, milli>(diff2).count() * 0.001 << " s" << endl;

    // Sequential sum
    C = createMatrix(n, true);
    auto start3 = chrono::steady_clock::now();
    matrixSumSquential(A, B, C, n);
    auto end3 = chrono::steady_clock::now();
    auto diff3 = end3 - start3;
    cout << "Sequential time for matrix sum: " << chrono::duration<double, milli>(diff3).count() * 0.001 << " s"
         << endl;

    printMatrix(A);
    cout << '\n';
    printMatrix(B);
    cout << '\n';
    printMatrix(C);

    return 0;
}
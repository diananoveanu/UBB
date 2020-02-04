#include <iostream>
#include <vector>
#include <thread>

using namespace std;

#define N 3

typedef int **Mat;

void printMatrix(Mat matrix) {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cout << matrix[i][j] << " ";
        }
        cout << '\n';
    }

    cout << "\n";
}


int computePoint(const Mat a, const Mat b, int i, int j) {
    int sum = 0;

    for (int k = 0; k < N; k++) {
        sum += a[i][k] * b[k][j];
    }

    return sum;
}


vector<pair<int, int> > splitWorkload(int n, int t) {
    vector<pair<int, int> > intervals;

    int index = 0;
    int step = n / t;
    int mod = n % t;

    while (index < n) {
        intervals.emplace_back(index, index + step + (mod > 0));
        index += step + (mod > 0);
        mod--;
    }

    return intervals;
}

ostream &operator<<(ostream &stream, vector<pair<int, int> > v) {
    for (int i = 0; i < v.size(); i++) {
        stream << i << ": " << v[i].first << " -> " << v[i].second << "\n";
    }

    return stream;
}


Mat multiply(const Mat a, const Mat b, int T) {

    Mat result = new int *[N];

    vector<thread> threads;
    vector<pair<int, int> > intervals = splitWorkload(N, T);
    cout << intervals;
    for (int idx = 0; idx < intervals.size(); idx++) {
        threads.emplace_back([intervals, a, b, idx, result]() {
                                 for (int i = intervals[idx].first; i < intervals[idx].second; i++) {
                                     result[i] = new int[N];
                                     for (int j = 0; j < N; j++) {
                                         result[i][j] = computePoint(a, b, i, j);
                                     }
                                 }
                             }
        );
    }

    for (int i = 0; i < T; i++) {
        threads[i].join();
    }

    return result;
}

Mat pow(const Mat a, int n, int T) {
    if (n == 1) {
        return a;
    }

    Mat temp = pow(a, n / 2, T);
    if (n % 2 == 0) {
        return multiply(temp, temp, T);
    } else {
        return multiply(a, multiply(temp, temp, T), T);
    }
}

int main() {

    Mat a = new int *[N];
    Mat b = new int *[N];
    Mat r = new int *[N];

    int k = 1;
    int k2 = 9;
    for (int i = 0; i < N; i++) {
        a[i] = new int[N];
        b[i] = new int[N];

        for (int j = 0; j < N; j++) {
            a[i][j] = k++;
            b[i][j] = k2--;
        }
    }

    int T = 1;

    r = pow(a, 2, 3);


    printMatrix(a);

    // r = multiply(a, b, 4);
    //printMatrix(b);
    printMatrix(r);
    for (int i = 0; i < N; i++) {
        delete a[i];
        delete r[i];
        delete b[i];
    }
    delete[] r;
    delete[] a;
    delete[] b;

    return 0;
}
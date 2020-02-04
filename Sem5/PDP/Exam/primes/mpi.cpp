//
// Created by Diana  on 2/4/20.
//

#include <iostream>
#include <vector>
#include <math.h>
#include "mpi.h"

using namespace std;

int main() {
    int n = 100, rank, num_proc;

    MPI_Init(NULL, NULL);
    MPI_Comm_size(MPI_COMM_WORLD, &num_proc);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    if (rank == 0) {
        num_proc--;

        int sqN = sqrt(n);
        vector<bool> primes(n, false);
        vector<int> firstPrimes;

        for (int x = 2; x <= sqN; x++) {
            bool isPrime = true;
            for (int y = 2; y <= x / 2; y++) {
                if (x % y == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                firstPrimes.push_back(x);
            }
        }
        for (int i = 0; i < firstPrimes.size(); i++) {
            primes[firstPrimes[i]] = true;
        }

        int newN = n - sqN;
        int per_process = newN / num_proc;
        int rem = newN % num_proc;
        int start = 0, stop = 0;


        int *vect_first_process;
        bool *local_result;
        vect_first_process = (int *) malloc(sizeof(int) * (sqN + 1));
        local_result = (bool *) malloc(sizeof(bool) * (n + 1));


        for (int i = 0; i < firstPrimes.size(); i++) {
            vect_first_process[i] = firstPrimes[i];
        }


        for (int i = 1; i <= num_proc; i++) {
            stop = start + per_process + (rem > 0);
            if (rem) {
                rem--;
            }

            int sz = firstPrimes.size();
            int sta = start + sqN;
            int stb = stop + sqN;
            MPI_Send(&sz, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
            MPI_Send(vect_first_process, sz, MPI_INT, i, 0, MPI_COMM_WORLD);

            MPI_Send(&n, 1, MPI_INT, i, 0, MPI_COMM_WORLD);

            MPI_Send(&sta, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
            MPI_Send(&stb, 1, MPI_INT, i, 0, MPI_COMM_WORLD);

            MPI_Recv(local_result, n, MPI_C_BOOL, i, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

            for (int k = 0; k < n; k++) {
                if (local_result[k])
                    primes[k] = true;
            }
            primes[0] = primes[1] = false;
            start = stop;
        }
        for (int k = 0; k < n; k++) {
            if (primes[k]) {
                cout << k << ' ';
            }
        }
        free(local_result);
        free(vect_first_process);

    } else {
        int sz, n, sta, stb;

        MPI_Recv(&sz, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
        int *recv_first_primes = (int *) malloc(sizeof(int) * sz); //allocate memory
        MPI_Recv(recv_first_primes, sz, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

        MPI_Recv(&n, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
        int *recv_bool = (int *) malloc(sizeof(int) * n);
        bool *fin = (bool *) malloc(sizeof(bool) * n);

        MPI_Recv(&sta, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
        MPI_Recv(&stb, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

        for (int j = sta; j < stb; j++) {
            bool is_prime = true;
            for (int i = 0; i < sz; i++) {
                if (j % recv_first_primes[i] == 0) {
                    is_prime = false;
                    break;
                }
            }
            if (is_prime) {
                fin[j] = true;
            }
        }

        MPI_Send(&fin, n, MPI_C_BOOL, 0, 0, MPI_COMM_WORLD);
        free(recv_bool);
        free(recv_first_primes);
        free(fin);
    }
    MPI_Finalize();
    return 0;
}
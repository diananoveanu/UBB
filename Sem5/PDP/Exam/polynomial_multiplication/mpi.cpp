//
// Created by Diana on 2/3/20.
//

#include <stdio.h>
#include "mpi.h"
#include <stdlib.h>
#include <vector>
#include <string>
#include <algorithm>
#include <iostream>

#define N 3
using namespace std;

int main() {
    int rank, num_proc;
    vector<int> a, b;
    a.push_back(1);
    a.push_back(2);
    a.push_back(3);
    b.push_back(1);
    b.push_back(2);
    b.push_back(3);
    int *finalResult = (int *) malloc((2 * N - 1) * sizeof(int));

    MPI_Init(NULL, NULL);
    MPI_Comm_size(MPI_COMM_WORLD, &num_proc);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    if (rank == 0) {
        num_proc--;
        int resultSize = 2 * N - 1;
        int *full_b;
        int *for_process_a;
        int *result = (int *) malloc(resultSize * sizeof(int));
        int per_process = a.size() / num_proc;
        int rem = a.size() % num_proc;
        int start = 0, stop = 0;

        for_process_a = (int *) malloc(sizeof(int) * (per_process + 1));
        full_b = (int *) malloc(sizeof(int) * (b.size()));

        for (int i = 0; i < b.size(); i++) {
            full_b[i] = b[i];
        }

        int size_full_b = b.size();

        for (int i = 1; i <= num_proc; i++) {
            stop = start + per_process;
            if (rem > 0) {
                stop += 1;
                rem -= 1;
            }
            for (int j = start; j < stop; j++) {
                for_process_a[j - start] = a[j]; //copy digits of a
            }

            int count = stop - start; //number of digits between start and stop
            // cout << "\nSTART: " << start << "\nSTOP: " << stop << "\n";
            MPI_Send(&resultSize, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
            MPI_Send(&count, 1, MPI_INT, i, 0, MPI_COMM_WORLD); //sending number of digits
            MPI_Send(&size_full_b, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
            MPI_Send(full_b, size_full_b, MPI_INT, i, 0, MPI_COMM_WORLD);
            MPI_Send(for_process_a, count, MPI_INT, i, 0, MPI_COMM_WORLD);
            MPI_Send(&start, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
            MPI_Recv(result, resultSize, MPI_INT, i, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

            for (int p = 0; p < resultSize; p++) {
                finalResult[p] += result[p];
            }

            start = stop;
        }
        for (int i = 0; i < resultSize; i++) {
            cout << finalResult[i] << ' ';
        }
        free(finalResult);
        free(result);
        free(for_process_a);
    } else {
        int count;
        int size_b;
        int start;
        int resultSize;

        MPI_Recv(&resultSize, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

        MPI_Recv(&count, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE); //recv number of digits
        MPI_Recv(&size_b, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

        int *full_b = (int *) malloc(sizeof(int) * (size_b));
        MPI_Recv(full_b, size_b, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

        int *recv_a = (int *) malloc(sizeof(int) * count); //allocate memory
        int *result = (int *) malloc(sizeof(int) * resultSize);
        for (int i = 0; i < resultSize; i++) {
            result[i] = 0;
        }
        MPI_Recv(recv_a, count, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE); //recv the digits from a
        MPI_Recv(&start, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE); //recv number of digits

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < size_b; j++) {
                int prod = recv_a[i] * full_b[j];
                result[start + i + j] += prod;
            }
        }
        MPI_Send(result, resultSize, MPI_INT, 0, 0, MPI_COMM_WORLD);

        free(result);
        free(full_b);
        free(recv_a);
    }
    MPI_Finalize();
    return 0;
}
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

using namespace std;

int main() {
    vector<int> a = {1, 2, 3, 4, 5}, b = {2, 3, 6, 1, 0};
    int sum, final_sum = 0, *for_process_a, *for_process_b, rank, num_proc;

    MPI_Init(NULL, NULL);
    MPI_Comm_size(MPI_COMM_WORLD, &num_proc);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    if (rank == 0) {
        num_proc--;
        int per_process = a.size() / num_proc;
        int rem = a.size() % num_proc;
        int start = 0, stop = 0;

        for_process_a = (int *) malloc(sizeof(int) * (per_process + 1));
        for_process_b = (int *) malloc(sizeof(int) * (per_process + 1));

        for (int i = 1; i <= num_proc; i++) {
            stop = start + per_process;
            if (rem > 0) {
                stop += 1;
                rem -= 1;
            }
            for (int j = start; j < stop; j++) {
                for_process_a[j - start] = a[j]; //copy digits of a
                for_process_b[j - start] = b[j]; //copy digits of b
            }

            int count = stop - start; //number of digits between start and stop
            MPI_Send(&count, 1, MPI_INT, i, 0, MPI_COMM_WORLD); //sending number of digits
            MPI_Send(for_process_a, count, MPI_INT, i, 0, MPI_COMM_WORLD); //sending count digits from a
            MPI_Send(for_process_b, count, MPI_INT, i, 0, MPI_COMM_WORLD); //sending count digits from b
            MPI_Recv(&sum, 1, MPI_INT, i, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
            start = stop;

            final_sum += sum;
        }
        cout << final_sum << '\n';
        free(for_process_a);
        free(for_process_b);
    } else {
        vector<int> sums(a.size(), 0);
        int count;

        MPI_Recv(&count, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE); //recv number of digits

        int *recv_a = (int *) malloc(sizeof(int) * count); //allocate memory
        int *recv_b = (int *) malloc(sizeof(int) * count);

        MPI_Recv(recv_a, count, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE); //recv the digits from a
        MPI_Recv(recv_b, count, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE); //recv the digits from b

        sum = 0;
        for (int i = 0; i < count; i++) {
            sum += recv_a[i] * recv_b[i];
        }

        MPI_Send(&sum, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
        free(recv_a);
        free(recv_b); //free vectors
    }
    MPI_Finalize();
    return 0;
}
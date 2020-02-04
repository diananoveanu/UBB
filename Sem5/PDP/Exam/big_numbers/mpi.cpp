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


int main() {
    int rank, numProc;
    int* forProcessFirst;
    int* forProcessSecond;
    std::vector<int> firstNumber;
    std::vector<int> secondNumber;
    firstNumber.push_back(9);
    firstNumber.push_back(1);
    firstNumber.push_back(1);
    secondNumber.push_back(9);
    secondNumber.push_back(9);
    secondNumber.push_back(9);
    MPI_Init(NULL, NULL);

    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &numProc);
    if(rank == 0){

        int* finalResult = (int*)malloc((2*N)* sizeof(int));
        memset (finalResult,0, sizeof(int)*(2*N));
        numProc--; //the master processes only reads and distributes the numbers there are num_proc - 1 proceses for calculation
        int perProcess = N / numProc; //per process
        int rem = N % numProc; //rem

        int start = 0, stop = 0;
        forProcessFirst = (int*)malloc(sizeof(int)*(N + 1)); //allocate memory for for_process_a
        forProcessSecond = (int*)malloc(sizeof(int)*(perProcess + 1)); //allocate memory for for_process_b
        for(int i = 0; i < firstNumber.size(); i++){
            forProcessFirst[i] = firstNumber[i];
        }
        int carry = 0;
        for (int i = 1; i <= numProc; i++) {
            stop = start + perProcess;
            if (rem > 0) {
                stop += 1;
                rem -= 1;
            }
            for (int j = start; j < stop; j++) {
                forProcessSecond[j - start] = secondNumber[j]; //copy digits of b
            }
            int count_b = stop - start; //number of digits between start and stop
            int count_a = firstNumber.size();
            int resultSize = 2 * N;
            int* localResult = (int*)malloc(resultSize * sizeof(int));
            MPI_Send(&resultSize, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
            MPI_Send(&count_a, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
            MPI_Send(&count_b, 1, MPI_INT, i, 0, MPI_COMM_WORLD); //sending number of digits
            MPI_Send(forProcessFirst, count_a, MPI_INT, i, 0, MPI_COMM_WORLD); //sending count digits from a
            MPI_Send(forProcessSecond, count_b, MPI_INT, i, 0, MPI_COMM_WORLD); //sending count digits from b
            MPI_Send(&start, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
            MPI_Recv(localResult, resultSize, MPI_INT, i, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE); //recv the calculated result
            carry = 0;

            for(int p = 0; p<resultSize - 1; p++){
                std::cout << finalResult[p] << '\n';
                finalResult[p] += localResult[p] + carry;
                carry = finalResult[p] / 10;
                finalResult[p] %= 10;
            }
            std::cout << '\n';
            start = stop;
            free(localResult);
        }

        if(carry){
            finalResult[2 * N - 1] = carry;
        }
        for(int i =2 * N - 1; i>=0; i--){
            std::cout << finalResult[i];
        }
        free(finalResult);
        free(forProcessFirst);
        free(forProcessSecond);
    }else{
        int count_b;
        int count_a;
        int start;
        int resultSize;
        MPI_Recv(&resultSize, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
        MPI_Recv(&count_a, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE); //recv number of digits
        MPI_Recv(&count_b, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE); //recv number of digits
        int* recvFirst = (int*)malloc(sizeof(int) * count_b); //allocate memory
        int* recvSecond = (int*)malloc(sizeof(int) * count_b);
        int* result = (int*)malloc(sizeof(int)* resultSize);
        MPI_Recv(recvFirst, count_a, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE); //recv the digits from a
        MPI_Recv(recvSecond, count_b, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE); //recv the digits from b
        MPI_Recv(&start, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

        memset(result, 0, resultSize*sizeof(int));
        for(int i = 0; i<count_b; i++){
            for(int j = 0; j<count_a; j++){
                int prod = recvFirst[j] * recvSecond[i];
                result[start + i + j] += prod;

            }
        }
        MPI_Send(result, resultSize, MPI_INT, 0, 0, MPI_COMM_WORLD); //sending the local_res
        free(result);
        free(recvFirst);
        free(recvSecond);
    }
    MPI_Finalize();
}
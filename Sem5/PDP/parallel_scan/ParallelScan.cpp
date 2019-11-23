//
// Created by Diana  on 11/23/19.
//

#include "ParallelScan.h"
#include <math.h>

std::vector<int> parallelScan(std::vector<int>& a) {
    return {};
}


void threadFunc(std::vector<int>* a){
    int step = pow(2, (*a).size());
    for(int k = 0; k < (*a).size(); k+= step){

    }
}

void upSweep(std::vector<int> *a) {
    for(int d = 0; d < (int)log2((*a).size()) - 1; d++){
        for all k = 0 to n – 1 by 2^(d+1) in parallel do
            x[k +  2^(d+1) – 1] = x[k +  2^d  – 1] + x[k +  2^(d+1) – 1]
    }
}

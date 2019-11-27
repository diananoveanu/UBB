//
// Created by Diana  on 11/23/19.
//

#ifndef PARALLEL_SCAN_PARALLELSCAN_H
#define PARALLEL_SCAN_PARALLELSCAN_H
#include <vector>


void upSweep(std::vector<int>* a, std::vector<int>* result);

std::vector<int> parallelScan(std::vector<int>& a);


#endif //PARALLEL_SCAN_PARALLELSCAN_H

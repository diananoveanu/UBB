#include <iostream>
#include <vector>
#include "ParallelScan.h"


std::vector<int> sequentialScan(const std::vector<int>& a){
    std::vector<int> b;
    b.push_back(a[0]);
    for(int i = 1; i<a.size(); i++){
        b.push_back(b[i - 1] + a[i]);
    }

    return b;
}

bool compareVectors(const std::vector<int>& a, const std::vector<int>& b){
    for(int i = 0; i<a.size(); i++){
        if(a[i] != b[i]){
            return false;
        }
    }

    return true;
}

void printVector(const std::vector<int>& a){
    for(const auto& x : a){
        std::cout << x << ' ';
    }
}

std::vector<int> untilPowerOfTwo(std::vector<int>& v){
    while(((v.size()) & (v.size() - 1)) != 0){
        v.push_back(0);
    }
    return v;
}

int main() {

    std::vector<int> v = {1, 2, 3, 4, 5, 10, 20, 30};
    v = untilPowerOfTwo(v);
    auto rezSeq = sequentialScan(v);
    auto rez = parallelScan(v);
    printVector(rezSeq);
    std::cout << '\n';
    printVector(rez);
    std::cout <<'\n';
    std::cout << (4 >> 1);
    return 0;
}
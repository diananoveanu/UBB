cmake_minimum_required(VERSION 3.15)
project(lab1_cpp)

set(CMAKE_CXX_STANDARD 14)



add_executable(lab1_cpp BigNum.cpp BigNum.h BigNumParal.cpp BigNumParal.h Helper.cpp Helper.h main.cpp
        numberDiff.cpp numberProd.cpp numberProd.h numberDiff.h)
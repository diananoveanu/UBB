cmake_minimum_required(VERSION 3.15)
project(parallel_scan)

set(CMAKE_CXX_STANDARD 14)

add_executable(parallel_scan main.cpp ParallelScan.cpp ParallelScan.h)
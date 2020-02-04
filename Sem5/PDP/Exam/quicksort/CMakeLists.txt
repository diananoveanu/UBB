cmake_minimum_required(VERSION 3.15)
project(quicksort)

set(CMAKE_CXX_STANDARD 14)

add_executable(quicksort threads.cpp threads.cpp)
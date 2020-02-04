cmake_minimum_required(VERSION 3.15)
project(permutations)

set(CMAKE_CXX_STANDARD 14)

add_executable(permutations threads.cpp mpi.cpp)
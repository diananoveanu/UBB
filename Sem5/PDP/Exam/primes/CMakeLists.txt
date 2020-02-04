cmake_minimum_required(VERSION 3.15)
project(primes)

set(CMAKE_CXX_STANDARD 14)

add_executable(primes threads.cpp mpi.cpp)
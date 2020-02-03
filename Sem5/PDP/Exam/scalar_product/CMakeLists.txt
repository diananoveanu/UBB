cmake_minimum_required(VERSION 3.15)
project(scalar_product)

set(CMAKE_CXX_STANDARD 14)

add_executable(scalar_product threads.cpp mpi.cpp)
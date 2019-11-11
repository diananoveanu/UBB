cmake_minimum_required(VERSION 3.15)
project(polynomial_multiplication)

set(CMAKE_CXX_STANDARD 14)

add_executable(polynomial_multiplication main.cpp Polynomial.cpp Polynomial.h Utils.cpp Utils.h)
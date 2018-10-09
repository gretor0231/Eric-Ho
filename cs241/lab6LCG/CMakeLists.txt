cmake_minimum_required(VERSION 3.10)
project(lab6LCG C)

set(CMAKE_C_STANDARD 90)

add_executable(lab6LCG cipher.c lcg.c)

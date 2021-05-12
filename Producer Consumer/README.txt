# The Producer Consumer Problem
Dev: Luke Miller
Dev Date: 4.16.2020
Class: Operating Systems
Establishment: University of Arkansas
This program uses threads, mutexes, and semaphores to solve the producer consumer problem. The user must input through the command line the amount of time it wants the program to run, the number of producer threads, and finally the number of consumer threads. The program then creates 2 semaphores which house 5 licenses each, and an unlocked mutex. It then creates the previously specified amount of threads which attempt to produce then consume 100 numbers in a bounded buffer (linked list) that never gets bigger than 5 indices long. How program terminates depending on whether it has created then destroyed all 100 numbers or has slept the amount of time specified.
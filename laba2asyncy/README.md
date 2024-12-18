# PrimeNumbersFinder

## Description

`PrimeNumbersFinder` is a Java program that calculates all prime numbers up to a given number `N` using multiple threads. The number `N` is provided by the user as input. The program divides the task of finding prime numbers into multiple threads to improve performance by parallelizing the computation.

The program uses the `ExecutorService` to manage a pool of threads, `Callable` tasks to compute prime numbers in parallel, and `Future` to collect the results from each thread. The prime numbers are stored in a `CopyOnWriteArrayList` to ensure thread-safe collection management.

## Features

- Finds all prime numbers up to a user-defined value `N`.
- Uses multithreading to divide the workload and speed up the calculation.
- Implements thread-safe collection handling using `CopyOnWriteArrayList`.
- Utilizes Java's `ExecutorService` for efficient thread management and `Callable` for concurrent tasks.

## Requirements

- Java 8 or higher.

## How to Run

1. Clone this repository or copy the code into your Java development environment.
2. Compile the code using a Java compiler:
   ```bash
   javac PrimeNumbersFinder.java

package com.dota;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PrimeNumbersFinder {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of N: ");
        int N = scanner.nextInt();
        int numThreads = 4;

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        List<Future<List<Integer>>> futures = new CopyOnWriteArrayList<>();

        int rangeSize = N / numThreads;
        for (int i = 0; i < numThreads; i++) {
            int start = i * rangeSize;
            int end = (i == numThreads - 1) ? N : (i + 1) * rangeSize;
            Callable<List<Integer>> task = new PrimeNumberTask(start, end);
            futures.add(executorService.submit(task));
        }

        CopyOnWriteArrayList<Integer> allPrimes = new CopyOnWriteArrayList<>();
        for (Future<List<Integer>> future : futures) {
            allPrimes.addAll(future.get());
        }

        System.out.println("Prime numbers up to " + N + ": " + allPrimes);
        executorService.shutdown();
    }

    static class PrimeNumberTask implements Callable<List<Integer>> {
        private final int start;
        private final int end;

        public PrimeNumberTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public List<Integer> call() {
            CopyOnWriteArrayList<Integer> primes = new CopyOnWriteArrayList<>();
            for (int i = start; i <= end; i++) {
                if (isPrime(i)) {
                    primes.add(i);
                }
            }
            return primes;
        }

        private boolean isPrime(int num) {
            if (num <= 1) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}

package com;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.State;

import java.util.ArrayList;
import java.util.List;


public class InsertionSort implements Algorithm{

    // sorts the array according to insertion sort algorithm and then returns k'th element
    @Override
    public int findK(List<Integer> input, int k) {
        List<Integer> arr = new ArrayList<>(input);
        for (int i = 1; i < arr.size(); i++) {
            int key = arr.get(i);
            int j = i - 1;

            while (j >= 0 && arr.get(j) > key) {
                arr.set(j+1, arr.get(j));
                j--;
            }
            arr.set(j+1, key);
        }
        return arr.get(k - 1);
    }
}

package com;

import java.util.*;

public class QuickSort {
    
    // returns k'th element in the input list
    public int findK(List<Integer> input, int k) {
        List<Integer> arr = new ArrayList<>(input);
        quickSort(arr, 0, arr.size() - 1);
        return arr.get(k - 1);
    }

    // assigns pivot first number and returns the index of the pivot which is
    // exactly in its correct index
    public int partition(List<Integer> arr, int first, int last) {
        int pivot = arr.get(first);
        while (first <= last) {
            while (arr.get(first) < pivot)
                first++;
            while (arr.get(last) > pivot)
                last--;
            if (first <= last) {
                Collections.swap(arr, first, last);
                first++;
                last--;
            }
        }
        return first;
    }

    // keeps executing recursion calls until sorting entire input list
    public void quickSort(List<Integer> arr, int first, int last) {
        int idx = partition(arr, first, last);
        if (first < idx - 1) {
            quickSort(arr, first, idx - 1);
        }
        if (last > idx) {
            quickSort(arr, idx, last);
        }
    }

}

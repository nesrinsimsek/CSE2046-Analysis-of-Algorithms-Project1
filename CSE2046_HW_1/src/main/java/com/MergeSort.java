package com;

import java.util.*;

public class MergeSort implements Algorithm {

    // sorts the array according to merge sort algorithm and then returns k'th
    // element
    public int findK(List<Integer> input, int k) {
        List<Integer> arr = new ArrayList<>(input);
        int size = arr.size();
        int kth = 0;

        // divides input list into two parts and merges
        if (size > 1) {
            List<Integer> firstHalf = new ArrayList<>(arr.subList(0, (size) / 2));
            List<Integer> secondHalf = new ArrayList<>(arr.subList((size) / 2, size));
            findK(firstHalf, k);
            findK(secondHalf, k);
            merge(firstHalf, secondHalf, arr);
        }

        if (size >= k) {
            kth = arr.get(k - 1);
        }
        return kth;
    }

    // merges two sublists in order
    public void merge(List<Integer> firstHalf, List<Integer> secondHalf, List<Integer> arr) {
        int i = 0;
        int j = 0;
        int k = 0;

        int firstHalfSize = firstHalf.size();
        int secondHalfSize = secondHalf.size();

        // compares the elements in two sublists and inserts smallest element into the
        // smallest index
        while (i < firstHalfSize && j < secondHalfSize) {
            if (firstHalf.get(i) <= secondHalf.get(j)) {
                arr.set(k, firstHalf.get(i));
                i++;
            } else {
                arr.set(k, secondHalf.get(j));
                j++;
            }
            k++;
        }

        // adds rest of the numbers (greater ones) to the end of the list
        if (i == firstHalfSize) {
            for (int x = j; x < secondHalfSize; x++) {
                arr.set(k, secondHalf.get(x));
                k++;
            }
        } else {
            for (int x = i; x < firstHalfSize; x++) {
                arr.set(k, firstHalf.get(x));
                k++;
            }
        }
    }

}

package com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuickSelect implements Algorithm {

    // returns k'th element in the input list
    public int findK(List<Integer> input, int k) {
        List<Integer> list = new ArrayList<>(input);
        int lastIndex = list.size() - 1;
        return quickSelect(list, 0, lastIndex, k);
    }

    // assigns pivot first number and returns the index of the pivot which is
    // exactly in its correct index
    public int partition(List<Integer> input, int left, int right, int first) {
        int pivot = input.get(first);

        Collections.swap(input, first, right);

        int pIndex = left;
        int i;

        for (i = left; i < right; i++) {

            if (input.get(i) <= pivot) {
                Collections.swap(input, i, pIndex);
                pIndex++;
            }
        }

        Collections.swap(input, pIndex, right);
        return pIndex;
    }

    // keeps executing recursion calls and until reaching k'th minimum number in the
    // input list and returns that number
    public int quickSelect(List<Integer> items, int first, int last, int k) {
        if (first == last)
            return items.get(first);
        int pivot = partition(items, first, last, first);

        if (k - 1 == pivot)
            return items.get(k - 1);
        else if (pivot < k) {
            return quickSelect(items, pivot + 1, last, k);

        } else {
            return quickSelect(items, first, pivot - 1, k);
        }

    }

}
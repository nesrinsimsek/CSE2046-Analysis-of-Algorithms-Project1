package com;

import java.util.*;

public class PartialSelectionSort implements Algorithm {

    // sorts input list until finding k'th element in the list and returns k'th element
    public int findK(List<Integer> input, int k) {
        List<Integer> arr = new ArrayList<>(input);
        for (int i = 0; i < k; i++) {
            int minIndex = i;
            int minValue = arr.get(i);

            // inserts the number into the correct index by comparing that number with all
            // previous numbers
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(j) < minValue) {
                    minIndex = j;
                    minValue = arr.get(j);
                    Collections.swap(arr, i, minIndex);

                }
            }
        }
        return arr.get(k - 1);
    }

}

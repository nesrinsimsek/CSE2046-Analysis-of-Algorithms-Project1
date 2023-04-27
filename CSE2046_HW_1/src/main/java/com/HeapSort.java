package com;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeapSort implements Algorithm {

    // returns k'th element in the input list
    @Override
    public int findK(List<Integer> input, int k) {
        List<Integer> list = new ArrayList<>(input); /*
                                                      * copies input list into another temp list not to change the
                                                      * original one
                                                      */
        int listSize = list.size();
        int elementK;
        maxHeap(list);

        // swaps first(maximum) and last element in the input list
        // and returns k'th element
        for (int i = listSize - 1; i >= k - 1; i--) {
            Collections.swap(list, i, 0);
            if (i == k - 1) {
                elementK = list.get(i);
                return elementK;
            }
            heapify(list, i, 0);
        }

        return 0;
    }

    // converts input list to max heap
    void maxHeap(List<Integer> list) {
        int listSize = list.size();

        for (int i = listSize / 2 - 1; i >= 0; i--) {
            heapify(list, listSize, i);
        }
    }

    // heapifies input list
    void heapify(List<Integer> list, int listSize, int index) {
        int root = index;
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        if (left < listSize && list.get(left) > list.get(root)) {
            root = left;
        }

        if (right < listSize && list.get(right) > list.get(root)) {
            root = right;
        }

        if (root != index) {
            Collections.swap(list, root, index);
            heapify(list, listSize, root);
        }
    }
}

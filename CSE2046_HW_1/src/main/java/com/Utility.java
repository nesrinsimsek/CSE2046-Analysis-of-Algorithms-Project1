package com;

import java.util.*;

public class Utility {


    // input list is created as random, ordered or reverse
    public static List<Integer> getList(int type,int size){
        
        List<Integer> list = switch (type) {
            case 0 -> randomInput(size);
            case 1 -> orderedInput(size);
            case 2 -> reverseInput(size);
            default -> null;
        };

        return list;
    }

    // returns k value to use as an index in the input list to reach minimum k'th element
    public static int getK(int x, int size) {
        int k;
        Random random = new Random();

        k = switch (x) {
            case 0 -> 1;
            case 1 -> random.nextInt(2, size / 2);
            case 2 -> size / 2;
            case 3 -> random.nextInt(size / 2 + 1, size);
            case 4 -> size;
            default -> -1;
        };

        return k;
    }

    // returns random input for given size
    public static List<Integer> randomInput(int size) {
        Random random = new Random();
        List<Integer> input = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            input.add(random.nextInt(10000));
        }
        return input;
    }

    // returns reversed input for given size
    public static List<Integer> reverseInput(int size) {
        List<Integer> input = orderedInput(size);
        for (int i = 0; i < Math.floor(size / 2.0); i++) {
            Collections.swap(input, i, size - i - 1);
        }
        return input;
    }

    // returns ordered input for given size
    public static List<Integer> orderedInput(int size) {
        List<Integer> input = randomInput(size);
        Collections.sort(input);
        return input;
    }

}

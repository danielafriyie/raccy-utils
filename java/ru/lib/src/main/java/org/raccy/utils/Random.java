package org.raccy.utils;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class Random {

    private Random() {

    }

    public static int randInt(int low, int high) {
        int value =  (int) ((Math.random() * (high - low)) + low);
        if (value > high)
            value = high;
        return value;
    }

    public static <E> List<E> choices(E[] input, int count) {
        int length = input.length;
        if (length == 0)
            return Arrays.asList(input);

        List<E> output = new ArrayList<>();

        java.util.Random rd = new java.util.Random();
        for (int i = 0; i < count; i++) {
            output.add(input[rd.nextInt(length)]);
        }

        return output;
    }

    public static  List<String> choices(String input, int count) {
        String[] arr = new String[input.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = String.valueOf(input.charAt(i));
        }
        return choices(arr, count);
    }

    public static String choices(String input, int count, boolean dummy) {
        List<String> output = choices(input, count);
        return String.join("", output);
    }

    public static <E> E choice(E[] input) {
        return choices(input, 1).get(0);
    }

    public static String shuffle(String input) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            list.add(String.valueOf(input.charAt(i)));
        }
        Collections.shuffle(list);
        return String.join("", list);
    }
}

package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        //dayOnePart1(FileHandler.readFile("1.txt"));
        //dayOnePart2(FileHandler.readFile("1.txt"));
        //dayTwoPart1(FileHandler.readFile("2.txt"));
        //dayTwoPart2(FileHandler.readFile("2.txt"));
        dayThree(FileHandler.readFile("3.txt"));
    }


    public static void dayOnePart1(String input) {
        int result = 0;
        String[] s = input.split("\n");
        for (String value : s) {
            char[] arr = value.toCharArray();
            int place = 0;
            int tmp = 0;
            for (int j = arr.length - 1; j > 0; j--) {
                tmp = tmp + (int) (Character.getNumericValue(arr[j]) * Math.pow(10, place));
                place++;
            }
            if (arr[0] == '-') tmp *= -1;
            result += tmp;
        }
        System.out.println("First: " + result);
    }

    public static void dayOnePart2(String input) {
        List<Integer> checked = new ArrayList<>();
        int result = 0;
        checked.add(result);
        String[] s = input.split("\n");
        int count = 0;
        while (true) {
            count++;
            for (int i = 0; i < s.length; i++) {
                char[] arr = s[i].toCharArray();
                int place = 0;
                int tmp = 0;
                for (int j = arr.length - 1; j > 0; j--) {
                    tmp = tmp + (int) (Character.getNumericValue(arr[j]) * Math.pow(10, place));
                    place++;
                }
                if (arr[0] == '-') tmp *= -1;
                result += tmp;

                if (checked.contains(result)) {
                    System.out.println("Result: " + result);
                    System.out.println("Loops: " + count);
                    return;
                }
                checked.add(result);
            }
        }
    }

    public static void dayTwoPart1(String input) {
        String[] s = input.split("\n");
        int twos = 0;
        int threes = 0;
        for (String each : s) {
            Map<Character, Integer> freq = new HashMap<>();
            boolean found2 = false;
            boolean found3 = false;
            for (int i = 0; i < each.length(); i++) {
                char c = each.charAt(i);
                freq.put(c, freq.getOrDefault(c, 0) + 1);
            }
            for (Map.Entry<Character, Integer> dict : freq.entrySet()) {

                if (!found2 && dict.getValue() == 2) {
                    twos++;
                    found2 = true;
                }
                if (!found3 && dict.getValue() == 3) {
                    threes++;
                    found3 = true;
                }
            }
        }
        System.out.println(twos * threes);
    }

    public static void dayTwoPart2(String input) {
        String[] s = input.split("\n");
        for (int i = 0; i < s.length; i++) {
            for (int j = 1; j < s.length; j++) {
                char[] one = s[i].toCharArray();
                char[] two = s[j].toCharArray();
                removeDifference(one, two);
            }
        }
    }

    static void removeDifference(char[] one, char[] two) {
        char[] result = new char[one.length];
        int count = 0;
        for (int i = 0; i < one.length; i++) {
            if (one[i] != two[i])
                count++;
        }
        if (count == 1) {
            for (int i = 0; i < one.length; i++) {
                if (one[i] == two[i]) {
                    result[i] = one[i];
                }

            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < result.length; i++){
                sb.append(result[i]);
            }
            System.out.println(sb.toString());
        }

    }

    static void dayThree(String input){
        String s[] = input.split("\n");
        //System.out.println(Arrays.toString(s));
        for(String each : s){
            char[] arr = each.toCharArray();
            // indexes describe:    [0] distance from left edge
            //                      [1] distance from top edge
            //                      |2] width of rectangle
            //                      [3] height of rectangle
            int[] values = getDimensions(arr);
            System.out.println(Arrays.toString(values));
        }
    }

    static int[] getDimensions(char[] arr){
        int[] values = new int[4];

        int point = 0;
        int tmp = 0;
        // Get distance from left edge
        for(int i = indexOf(arr, ',') - 1; arr[i] != ' '; i--){
            tmp = tmp + Character.getNumericValue(arr[i]) * (int)Math.pow(10, point);
            point++;
        }
        values[0] = tmp;

        point = 0;
        tmp = 0;
        // Get distance from top edge
        for(int i = indexOf(arr, ':') - 1; arr[i] != ','; i--){
            tmp = tmp + Character.getNumericValue(arr[i]) * (int)Math.pow(10, point);
            point++;
        }
        values[1] = tmp;

        point = 0;
        tmp = 0;
        // Get the width of the rectangle
        for(int i = indexOf(arr, 'x') - 1; arr[i] != ' '; i--){
            tmp = tmp + Character.getNumericValue(arr[i]) * (int)Math.pow(10, point);
            point++;
        }
        values[2] = tmp;

        point = 0;
        tmp = 0;
        // Get the height of the rectangle
        for(int i = arr.length - 1; arr[i] != 'x'; i--){
            tmp = tmp + Character.getNumericValue(arr[i]) * (int)Math.pow(10, point);
            point++;
        }
        values[3] = tmp;

        return values;
    }

    // Returns -1 if element is not in array
    static int indexOf(char[] arr, char c){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == c) return i;
        }
        return -1;
    }
}

package com.company;

import com.sun.prism.image.Coords;

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
                Helper.removeDifference(one, two);
            }
        }
    }

    static void dayThree(String input){
        String s[] = input.split("\n");
        int[][] m = new int[1001][999];
        for(int k = 0; k < s.length; k++){
            char[] arr = s[k].toCharArray();
            // indexes describe:    [0] distance from left edge
            //                      [1] distance from top edge
            //                      |2] width of rectangle
            //                      [3] height of rectangle
            int[] values = Helper.getDimensions(arr);
            for(int i = values[0]; i < values[0] + values[2]; i++){
                for(int j = values[1]; j < values[1] + values[3]; j++){
                    m[i][j]++;
                }
            }

        }
        for(int i = 0; i < s.length; i++){
            char[] arr = s[i].toCharArray();
            int[] values = Helper.getDimensions(arr);
            if(Helper.isUnique(values, m)) System.out.println("Part two: " + (i + 1));
        }
        int count = 0;
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[0].length; j++){
                if(m[i][j] >= 2) count++;
            }
        }
        System.out.println("Part one: " + count);
    }
}

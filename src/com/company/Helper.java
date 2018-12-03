package com.company;

/**
 * Created by JakobErlandsson on 2018-12-03.
 */
public class Helper {

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

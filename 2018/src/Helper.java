import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by JakobErlandsson on 2018-12-03.
 */
public class Helper {

    // Used in problem 2.2
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

    // Used in problem 3.1
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

    // Returns index of the first occurence of an element c in an
    // array arr. Returns -1 if element is not in array.
    static int indexOf(char[] arr, char c){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == c) return i;
        }
        return -1;
    }

    // Used for problem 3.2
    static boolean isUnique(int[] values, int[][] m){
        for(int i = values[0]; i < values[0] + values[2]; i++){
            for(int j = values[1]; j < values[1] + values[3]; j++){
                if(m[i][j] != 1) return false;
            }
        }
        return true;
    }

    static int getGuardID(char[] arr){
        int ID = 0;
        int point = 0;
        for(int i = indexOf(arr, 'b') - 2; arr[i] != '#'; i--){
            ID += Character.getNumericValue(arr[i]) * Math.pow(10, point);
            point++;
        }
        return ID;
    }

    // Used in problem 5.1, returns true if c1 and c2 are equal in letter
    // but differs in case, else returns false
    static boolean match(char c1, char c2){
        char[] arr1 = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] arr2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        if (Character.isUpperCase(c2)){
            return indexOf(arr2, c2) == indexOf(arr1, c1);
        }
        else{
            return indexOf(arr2, c1) == indexOf(arr1, c2);
        }
    }

    // Used in problem 5.2, returns an array without both upper and lower case
    // of character lower
    static char[] removeLetter(char[] arr, char lower){
        char upper = Character.toUpperCase(lower);
        int count = 0;
        for(char c : arr){
            if(c == lower || c == upper) count++;
        }
        char[] tmp = new char[arr.length - count];
        int i = 0;
        for(char c : arr){
            if(c != lower && c != upper){
                tmp[i] = c;
                i++;
            }
        }
        return tmp;
    }

    static boolean isDone(List<Character> l, char[] arr, boolean[] visited){
        Iterator it = l.iterator();
        while(it.hasNext()){
            if(!visited[indexOf(arr, (char)it.next())]) return false;
        }
        return true;
    }


}

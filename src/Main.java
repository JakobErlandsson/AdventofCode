import java.util.*;

public class Main {

    public static void main(String[] args) {

        //dayOnePart1(FileHandler.readFile("1.txt"));
        //dayOnePart2(FileHandler.readFile("1.txt"));
        //dayTwoPart1(FileHandler.readFile("2.txt"));
        //dayTwoPart2(FileHandler.readFile("2.txt"));
        //dayThree(FileHandler.readFile("3.txt"));
        //dayFour(FileHandler.readFile("4.txt"));
        System.out.println("Part one: " + dayFivePart1(FileHandler.readFile("5.txt").toCharArray()));
        dayFivePart2(FileHandler.readFile("5.txt"));
        //System.out.println(Arrays.toString(Helper.removeLetter(FileHandler.readFile("5.txt").toCharArray(), 'a', 'A')));
    }


    static void dayOnePart1(String input) {
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

    static void dayOnePart2(String input) {
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

    static void dayTwoPart1(String input) {
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

    static void dayTwoPart2(String input) {
        String[] s = input.split("\n");
        for (int i = 0; i < s.length; i++) {
            for (int j = 1; j < s.length; j++) {
                char[] one = s[i].toCharArray();
                char[] two = s[j].toCharArray();
                Helper.removeDifference(one, two);
            }
        }
    }

    static void dayThree(String input) {
        String s[] = input.split("\n");
        int[][] m = new int[1001][999];
        for (int k = 0; k < s.length; k++) {
            char[] arr = s[k].toCharArray();
            // indexes describe:    [0] distance from left edge
            //                      [1] distance from top edge
            //                      |2] width of rectangle
            //                      [3] height of rectangle
            int[] values = Helper.getDimensions(arr);
            for (int i = values[0]; i < values[0] + values[2]; i++) {
                for (int j = values[1]; j < values[1] + values[3]; j++) {
                    m[i][j]++;
                }
            }

        }
        for (int i = 0; i < s.length; i++) {
            char[] arr = s[i].toCharArray();
            int[] values = Helper.getDimensions(arr);
            if (Helper.isUnique(values, m)) System.out.println("Part two: " + (i + 1));
        }
        int count = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] >= 2) count++;
            }
        }
        System.out.println("Part one: " + count);
    }

    static void dayFour(String input) {
        boolean asleep = false;
        Guard[] guards = new Guard[5000];
        int fellAsleep = 0;
        String[] s = input.split("\n");
        int activeGuard = 0;
        Arrays.sort(s);
        //System.out.println(Arrays.toString(s));
        for (String each : s) {
            char[] a = each.toCharArray();
            //System.out.println(Arrays.toString(a));
            int minute = Character.getNumericValue(a[16]) + 10 * Character.getNumericValue(a[15]);
            if (a[19] == 'G') {
                activeGuard = Helper.getGuardID(a);
                if (guards[activeGuard] == null) {
                    guards[activeGuard] = new Guard(activeGuard);
                }
            } else if (a[19] == 'f') {
                fellAsleep = minute;
            } else {   //(a[19] == 'w')
                guards[activeGuard].minutesAsleep += (minute - fellAsleep) % 60;
                for (int i = 0; i < (minute - fellAsleep) % 60; i++) {
                    guards[activeGuard].nAsleep[(fellAsleep + i) % 60]++;
                }
            }
        }
        int highest = 0;
        int highestID = 0;
        int highest2 = 0;
        int highestMinute = 0;
        for (Guard g : guards) {
            if (g != null) {
                if (g.minutesAsleep >= highest) {
                    highest = g.minutesAsleep;
                    highestID = g.id;
                }
            }
        }
        for (Guard g : guards) {
            if (g != null) {
                if (g.id == highestID) {
                    for (int i = 0; i < g.nAsleep.length; i++) {
                        if (g.nAsleep[i] >= highest2) {
                            highest2 = g.nAsleep[i];
                            highestMinute = i;
                        }
                    }
                }
            }
        }
        int result1 = highestID * highestMinute;
        System.out.println("Part one: " + highestID + " * " + highestMinute + " = " + result1);

        for (Guard g : guards) {
            if (g != null) {
                highestMinute = 0;
                int numHighest = 0;
                for (int i = 0; i < g.nAsleep.length; i++) {
                    if (g.nAsleep[i] >= numHighest) {
                        numHighest = g.nAsleep[i];
                        highestMinute = i;
                    }
                }
                g.minuteMostAsleep[0] = highestMinute;
                g.minuteMostAsleep[1] = numHighest;
            }
        }
        int id = 0;
        int y = 0;
        for (Guard g : guards) {
            if (g != null) {
                if (g.minuteMostAsleep[1] >= y) {
                    y = g.minuteMostAsleep[1];
                    id = g.id;
                }
            }
        }
        int result2 = id * guards[id].minuteMostAsleep[0];
        System.out.println("Part two: " + id + " * " + guards[id].minuteMostAsleep[0] + " = " + result2);
    }

    static int dayFivePart1(char[] input) {
        int diff = 1;
        while(diff > 0) {
            for (int i = 0; i < input.length - 1; i++) {
                if (Helper.match(input[i], input[i + 1])) {
                    input[i] = '$';
                    input[i + 1] = '$';
                }
            }
            int count = 0;
            for (char c : input) {
                if (c != '$') count++;
            }
            int i = 0;
            char[] tmp = new char[count];
            for (char c : input) {
                if (c != '$') {
                    tmp[i] = c;
                    i++;
                }
            }
            diff = input.length - tmp.length;
            input = tmp;
        }
        return input.length;
    }

    static void dayFivePart2(String input){
        char[] a = input.toCharArray();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int shortest = 12000;
        for(char c : alphabet){
            char[] tmp = Helper.removeLetter(a, c);
            int length = dayFivePart1(tmp);
            if(length <= shortest){
                shortest = length;
            }
        }
        System.out.println("Part two: " + shortest);
    }
}

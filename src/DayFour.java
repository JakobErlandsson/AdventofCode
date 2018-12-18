import java.util.Arrays;

/**
 * Created by JakobErlandsson on 2018-12-16.
 */
public class DayFour {

    static void dayFour(String input) {
        boolean asleep = false;
        Guard[] guards = new Guard[5000];
        int fellAsleep = 0;
        String[] s = input.split("\n");
        int activeGuard = 0;
        Arrays.sort(s);
        for (String each : s) {
            char[] a = each.toCharArray();
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

    public static class Guard {

        int id;
        int[] minuteMostAsleep;
        int[] nAsleep;
        int minutesAsleep;

        Guard(int id){
            this.id = id;
            nAsleep = new int[60];
            minuteMostAsleep = new int[2];
            minutesAsleep = 0;
            for(int i = 0; i < nAsleep.length; i++){
                nAsleep[i] = 0;
            }
        }
    }
}

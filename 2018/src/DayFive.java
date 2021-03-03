/**
 * Created by JakobErlandsson on 2018-12-16.
 */
public class DayFive {

    static int dayFivePart1(char[] input) {
        int diff = 1;
        int loops = 0;
        while (diff > 0) {
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
            loops++;
        }
        //System.out.println("Loops: " + loops);
        return input.length;
    }

    static void dayFivePart2(String input) {
        char[] a = input.toCharArray();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int shortest = a.length;
        char bestLetter = '0';
        for (char c : alphabet) {
            char[] tmp = Helper.removeLetter(a, c);
            System.out.print(c + ": ");
            int length = dayFivePart1(tmp);
            if (length <= shortest) {
                shortest = length;
                bestLetter = c;
            }
        }
        System.out.println("Part two: " + shortest + " by removing " + bestLetter);
    }
}

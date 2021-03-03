import java.util.HashMap;
import java.util.Map;

/**
 * Created by JakobErlandsson on 2018-12-16.
 */
public class DayTwo {

    public DayTwo(String input){
        dayTwoPart1(FileHandler.readFile("2.txt"));
        dayTwoPart2(FileHandler.readFile("2.txt"));
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
}

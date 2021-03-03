import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by JakobErlandsson on 2018-12-16.
 */
public class DayTwelve {

    static void dayTwelve(String input) {
        String[] s = input.split("\n");
        HashMap<Integer, Integer> pots = new HashMap<>();
        HashMap<Integer, Integer> rules = new HashMap<>();
        List<Integer> changes = new LinkedList<>();
        // Get initial state
        int index = 0;
        for (int i = 0; i < s[0].length(); i++) {
            if (s[0].charAt(i) != '#' && s[0].charAt(i) != '.') continue;
            pots.put(index, s[0].charAt(i) == '#' ? 1 : 0);
            index++;
        }
        // Get set of rules
        for (String each : s) {
            if (each.length() > 1)
                rules.put(getRuleNo(each), each.charAt(each.length() - 1) == '#' ? 1 : 0);
        }


        long count = 0;
        long prev = 0;
        for (int j = 0; j < 2000; j++) {

            int lowestPot = getLowestKey(pots);
            int highestPot = getHighestKey(pots);
            for (Integer i : pots.keySet()) {
                int key;
                if(i == lowestPot)
                    key = pots.get(i) * 4 + pots.get(i+1) * 2 + pots.get(i+2);
                else if(i == lowestPot + 1)
                    key = pots.get(i-1) * 8 + pots.get(i) * 4 + pots.get(i+1) * 2 + pots.get(i+2);
                else if(i == highestPot - 1)
                    key = pots.get(i-2) * 16 + pots.get(i-1) * 8 + pots.get(i) * 4 + pots.get(i+1) * 2;
                else if(i == highestPot)
                    key = pots.get(i-2) * 16 + pots.get(i-1) * 8 + pots.get(i) * 4;
                else if(i < highestPot - 1 && i > lowestPot + 1)
                    key = pots.get(i-2) * 16 + pots.get(i-1) * 8 + pots.get(i) * 4 + pots.get(i+1) * 2 + pots.get(i+2);
                else
                    continue;

                if(!pots.get(i).equals(rules.get(key)))
                    changes.add(i);
            }

            int leftOfLowest = 0;
            if(pots.get(lowestPot + 1) == 0) leftOfLowest = 1;

            for (Integer i : changes) {
                pots.put(i, pots.get(i) == 1 ? 0 : 1);
            }
            changes.clear();

            pots.put(highestPot + 1, 1);
            pots.put(lowestPot - 1, leftOfLowest);
            count = 0;
            for (Integer i : pots.keySet()) {
                if (pots.get(i) == 1) {
                    count += i;
                }
            }
            long diff = count - prev;
            prev = count;
            System.out.println(count + "\t" + diff);
        }
        System.out.println("\n" + (count + (50000000000L - 2000) * 186));
    }

    static void dayTwelvePartTwo(String input){
        String[] s = input.split("\n");
        HashMap<Integer, Integer> rules = new HashMap<>();
        for (String each : s) {
            if (each.length() > 1)
                rules.put(getRuleNo(each), each.charAt(each.length() - 1) == '#' ? 1 : 0);
        }
        long pots = getInitialState(s[0]);
        System.out.println(pots);


    }

    static int getHighestKey(HashMap<Integer, Integer> map) {
        int key = 0;
        for (Integer i : map.keySet()) {
            if (i > key && map.get(i) == 1) key = i;
        }
        return key;
    }

    static int getLowestKey(HashMap<Integer, Integer> map) {
        int key = 1000000000;
        for (Integer i : map.keySet()) {
            if (i < key && map.get(i) == 1) key = i;
        }
        return key;
    }

    static int getRuleNo(String s) {
        int result = 0;
        for (int i = 0; i < 5; i++) {
            if (s.charAt(4 - i) == '#')
                result += Math.pow(2, i);
        }
        return result;
    }

    static long getInitialState(String s){
        int index = 0;
        long result = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            if(s.charAt(i) == '#'){
                result += Math.pow(2, index);
                index++;
            }
        }
        return result;
    }
}

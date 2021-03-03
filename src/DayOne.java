import java.util.TreeSet;

/**
 * Created by JakobErlandsson on 2018-12-16.
 */
public class DayOne {

    public DayOne(){
        dayOnePart1(FileHandler.readFile("1.txt"));
        dayOnePart2(FileHandler.readFile("1.txt"));
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
        System.out.println("Day one part one: " + result);
    }

    static void dayOnePart2(String input) {
        TreeSet<Integer> checked = new TreeSet<>();
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
                    System.out.println("Day one part two: " + result + " after " + count + " loops at " + checked.size());
                    return;
                }
                checked.add(result);
            }
        }
    }
}

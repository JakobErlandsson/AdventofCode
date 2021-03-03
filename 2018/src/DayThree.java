/**
 * Created by JakobErlandsson on 2018-12-16.
 */
public class DayThree {

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
}

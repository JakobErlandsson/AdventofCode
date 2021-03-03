import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by JakobErlandsson on 2018-12-16.
 */
public class DayEigth {

    static void dayEight(String input) {
        String[] s = input.split(" ");
        List<Integer> l = new LinkedList<>();
        for (int i = 0; i < s.length - 1; i++) {
            char[] arr = s[i].toCharArray();
            if (arr.length == 1) l.add(Character.getNumericValue(arr[0]));
            else {
                int point = 0;
                int num = 0;
                for (int j = arr.length - 1; j >= 0; j--) {
                    num += Character.getNumericValue(arr[j]) * Math.pow(10, point);
                    point++;
                }
                l.add(num);
            }
        }
        l.add(1);

        Iterator<Integer> it = l.iterator();
        int sum = 0;
        while (it.hasNext()) {
            sum += recTree(it, it.next(), it.next());
        }
        System.out.println(sum);
    }

    private static int recTree(Iterator<Integer> it, int node, int meta) {
        int sum = 0;
        // Find the nodes and add the meta
        for (int j = 0; j < node; j++) {
            sum += recTree(it, it.next(), it.next());
        }
        // Add the meta
        for (int i = 0; i < meta; i++) {
            sum += it.next();
        }

        return sum;
    }
}

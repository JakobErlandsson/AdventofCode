import java.awt.*;
import java.util.HashMap;

/**
 * Created by JakobErlandsson on 2018-12-16.
 */
public class DaySix {

    static void daySix(String input) {
        String[] s = input.split("\n");
        HashMap<Integer, Point> points = new HashMap<>();
        int maxx = 0;
        int maxy = 0;
        for (int j = 0; j < s.length; j++) {
            char[] arr = s[j].toCharArray();
            int x = 0;
            int y = 0;
            int point = 0;
            for (int i = arr.length - 1; arr[i] != ' '; i--) {
                y += Character.getNumericValue(arr[i]) * Math.pow(10, point);
                point++;
            }
            point = 0;
            for (int i = Helper.indexOf(arr, ',') - 1; i >= 0; i--) {
                x += Character.getNumericValue(arr[i]) * Math.pow(10, point);
                point++;
            }
            if (x > maxx) {
                maxx = x;
            }
            if (y > maxy) {
                maxy = y;
            }
            points.put(j, new Point(x, y));
        }
        int[][] grid = new int[maxx + 1][maxy + 1];
        HashMap<Integer, Integer> regions = new HashMap<>();
        for (int x = 0; x <= maxx; x++) {
            for (int y = 0; y <= maxy; y++) {

                int best = maxx + maxy;
                int bestnum = -1;

                // find distance to closest point
                for (int i = 0; i < s.length; i++) {
                    Point p = points.get(i);

                    int dist = Math.abs(x - p.x) + Math.abs(y - p.y);
                    if (dist < best) {
                        best = dist;
                        bestnum = i;
                    } else if (dist == best) {
                        bestnum = -1;
                    }
                }

                grid[x][y] = bestnum;
                Integer total = regions.get(bestnum);
                if (total == null) {
                    total = new Integer(1);
                } else {
                    total = total.intValue() + 1;
                }
                regions.put(bestnum, total);
            }
        }

        // remove infinite
        for (int x = 0; x <= maxx; x++) {
            int bad = grid[x][0];
            regions.remove(bad);
            bad = grid[x][maxy];
            regions.remove(bad);
        }
        for (int y = 0; y <= maxy; y++) {
            int bad = grid[0][y];
            regions.remove(bad);
            bad = grid[maxx][y];
            regions.remove(bad);
        }

        // find biggest
        int biggest = 0;
        for (int size : regions.values()) {
            if (size > biggest) {
                biggest = size;
            }
        }

        System.out.println("Biggest: " + biggest);

        int inarea = 0;

        for (int x = 0; x <= maxx; x++) {
            for (int y = 0; y <= maxy; y++) {

                int size = 0;
                for (int i = 0; i < s.length; i++) {
                    Point p = points.get(i);
                    int dist = Math.abs(x - p.x) + Math.abs(y - p.y);
                    size += dist;
                }

                if (size < 10000) {
                    inarea++;
                }
            }
        }
        System.out.println("Area Size: " + inarea);
    }
}

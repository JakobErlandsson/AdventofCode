import java.awt.*;
import java.awt.List;
import java.util.*;

/**
 * Created by JakobErlandsson on 2018-12-16.
 */
public class DayTen {

    static void dayTen(String input) {
        String[] s = input.split("\n");
        java.util.List<Point> pList = new LinkedList<>();
        java.util.List<Point> vList = new LinkedList<>();
        int highestX = 0;
        int highestY = 0;
        int lowestX = 0;
        int lowestY = 0;
        for (String each : s) {
            char[] arr = each.toCharArray();
            int x = getValue(arr, ',', ' ');
            int y = getValue(arr, '>', ' ');
            if (x > highestX) highestX = x;
            if (x < lowestX) lowestX = x;
            if (y > highestY) highestY = y;
            if (y < lowestY) lowestY = y;
            int vx = Character.getNumericValue(arr[arr.length - 6]);
            if (arr[arr.length - 7] == '-') vx *= -1;
            int vy = Character.getNumericValue(arr[arr.length - 2]);
            if (arr[arr.length - 3] == '-') vy *= -1;
            pList.add(new Point(x, y));
            vList.add(new Point(vx, vy));
        }
        int time = 0;
        while (getHeight(pList) > 10) {
            int i = 0;
            for (Point p : pList) {
                int dx = vList.get(i).x;
                int dy = vList.get(i).y;
                p.translate(dx, dy);
                i++;
            }
            time++;
        }

        highestX = -1000000;
        highestY = -1000000;
        lowestX = 1000000;
        lowestY = 1000000;

        for (Point p : pList) {
            if (p.x > highestX) highestX = p.x;
            if (p.x < lowestX) lowestX = p.x;
            if (p.y > highestY) highestY = p.y;
            if (p.y < lowestY) lowestY = p.y;
        }

        char[][] grid = new char[getHeight(pList) + 1][getWidth(pList) + 1];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = ' ';
            }
        }
        for (Point p : pList) {
            grid[p.y - lowestY][p.x - lowestX] = '*';
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        System.out.println(time);
    }

    static int getHeight(java.util.List<Point> l) {
        int highest = -100000;
        int lowest = 1000000;
        for (Point p : l) {
            if (p.y > highest) highest = p.y;
            if (p.y < lowest) lowest = p.y;
        }
        return highest - lowest;
    }

    static int getWidth(java.util.List<Point> l) {
        int highest = -100000;
        int lowest = 1000000;
        for (Point p : l) {
            if (p.x > highest) highest = p.x;
            if (p.x < lowest) lowest = p.x;
        }
        return highest - lowest;
    }

    static int getValue(char[] arr, char c1, char c2) {
        int count = 0;
        int point = 0;
        for (int i = Helper.indexOf(arr, c1) - 1; arr[i] != c2; i--) {
            count += Character.getNumericValue(arr[i]) * Math.pow(10, point);
            point++;
            if (arr[i - 1] == '-') {
                return -count;
            }
        }
        return count;
    }
}

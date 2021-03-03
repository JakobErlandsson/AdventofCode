import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by JakobErlandsson on 2018-12-20.
 */
public class DaySeventeen {

    DaySeventeen(){daySeventeen();}

    String input = FileHandler.readFile("17.txt");
    String[] s = input.split("\n");
    List<Point> clay = new LinkedList<>();
    int highestY = 0;
    int highestX = 0;
    int lowestY = 1000;
    int lowestX = 1000;

    void daySeventeen(){
        for(int i = 0; i < s.length; i++){
            if(s[i].charAt(0) == 'y'){
                int y = getFirst(s[i]);
                if(y < lowestY) lowestY = y;
                if(y > highestY) highestY = y;
                for(int x = low(s[i]); x <= high(s[i]); x++){
                    clay.add(new Point(x, y));
                }
            }
            else{
                int x = getFirst(s[i]);
                if(x < lowestX) lowestX = x;
                if(x > highestX) highestX = x;
                for(int y = low(s[i]); y <= low(s[i]); y++){
                    clay.add(new Point(x, y));
                }
            }
        }
        char[][] map = new char[highestX-lowestX][highestY-lowestY];
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(clay.contains(new Point(j,i))) map[i][j] = '#';
                else map[i][j] = '.';
            }
        }
    }

    int getFirst(String s){
        int result = 0;
        int point = 0;
        for(int i = s.indexOf(",") - 1; s.charAt(i) != '='; i--){
           result += Character.getNumericValue(s.charAt(i)) * Math.pow(10, point);
           point++;
        }
        return result;
    }

    int low(String s){
        int result = 0;
        int point = 0;
        for(int i = s.indexOf('.') - 1; s.charAt(i) != '='; i--){
            result += Character.getNumericValue(s.charAt(i)) * Math.pow(10, point);
            point++;
        }
        return result;
    }

    int high(String s){
        int result = 0;
        int point = 0;
        for(int i = s.length() - 1; s.charAt(i) != '.'; i--){
            result += Character.getNumericValue(s.charAt(i)) * Math.pow(10, point);
            point++;
        }
        return result;
    }


}

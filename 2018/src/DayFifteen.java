import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by JakobErlandsson on 2018-12-19.
 */
public class DayFifteen {

    public enum Type {
        ELF,
        GOBLIN
    }

    public DayFifteen() {
        dayFifteen();
    }

    String input = FileHandler.readFile("15.txt");
    String[] s = input.split("\n");
    char[][] map = new char[32][32];
    int nElf = 0;
    int nGob = 0;
    HashMap<Point, Unit> units = new HashMap<>();
    List<Unit> adjacent = new LinkedList<>();

    void dayFifteen() {
        init();
        while (nElf > 0 && nGob > 0) {
            Unit current = getFirstAvailableUnit();
            adjacent = getAdjacent(current);
            if(!adjacent.isEmpty()){

            }
        }
    }

    List<Unit> getAdjacent(Unit u){
        List<Unit> adjacent = new LinkedList<>();
        int posX = u.position.x;
        int posY = u.position.y;
        Point up = new Point(posX, posY-1);
        Point left = new Point(posX-1, posY);
        Point right = new Point(posX+1, posY);
        Point down = new Point(posX, posY+1);
        if(units.get(up) != null && units.get(up).type != u.type) adjacent.add(units.get(up));
        if(units.get(left) != null && units.get(left).type != u.type) adjacent.add(units.get(left));
        if(units.get(right) != null && units.get(right).type != u.type) adjacent.add(units.get(right));
        if(units.get(down) != null && units.get(down).type != u.type) adjacent.add(units.get(down));
        return adjacent;

    }

    Unit getFirstAvailableUnit() {
        Point tmp = new Point(1000, 1000);
        for(Point p : units.keySet()){
            if(units.get(p).moved) continue;
            if(p.y < tmp.y) tmp = p;
            if(p.y == tmp.y){
                if(p.x < tmp.x) tmp = p;
            }
        }
        return units.get(tmp);
    }

    void init() {
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length(); j++) {
                if (s[i].charAt(j) == 'G') {
                    units.put(new Point(j, i),(new Unit(new Point(j, i), Type.GOBLIN)));
                    nGob++;
                } else if (s[i].charAt(j) == 'E') {
                    units.put(new Point(j, i),(new Unit(new Point(j, i), Type.ELF)));
                    nElf++;
                }
                map[j][i] = s[i].charAt(j);
            }
        }
    }

    private class Unit {

        Point position;
        int health;
        int power;
        Type type;
        boolean moved;


        public Unit(Point pos, Type t) {
            position = pos;
            health = 200;
            power = 3;
            type = t;
            moved = false;
        }
    }

}

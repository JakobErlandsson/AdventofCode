
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by JakobErlandsson on 2018-12-16.
 */
public class DayThirteen {

    DayThirteen() {
        dayThirteen();
    }

    String input = FileHandler.readFile("13.txt");
    //String input = FileHandler.readFile("13test.txt");
    String[] s = input.split("\n");
    List<List<Character>> map = new LinkedList<>();
    List<Cart> carts = new LinkedList<>();
    List<Cart> crashes = new LinkedList<>();

    public void dayThirteen() {
        init();
//        for(int i = 0; i < map.size(); i++){
//            for(int j = 0; j < map.get(i).size(); j++){
//                System.out.print(map.get(i).get(j));
//            }
//            System.out.println();
//        }
        while (carts.size() > 1) {
            for (int i = 0; i < carts.size(); i++) {
                crashed();
                Cart current = getTopCart();
                if(crashes.contains(current)){
                    current.moved = true;
                    continue;
                }

                char nextPath = getNextPath(current);
                current.move();
                if (nextPath != '-' && nextPath != '|') {
                    current.turn(nextPath);
                }
                current.moved = true;
            }

            carts.removeAll(crashes);
            for (Cart c : carts) {
                c.moved = false;
            }

//            for(Cart c : carts){
//                System.out.println(c.position.x + "," + c.position.y);
//            }
//            System.out.println();

        }
        System.out.println(carts.get(0).position.x + "," + carts.get(0).position.y);
    }

    private void crashed() {
        crashes.clear();
        for (int i = 0; i < carts.size() - 1; i++) {
            for (int j = i + 1; j < carts.size(); j++) {
                if (carts.get(i).position.equals(carts.get(j).position)) {
                    crashes.add(carts.get(i));
                    crashes.add(carts.get(j));
                }
            }
        }
    }

    private Cart getTopCart() {
        Cart top = new Cart(new Point(1000, 1000), 'v');
        for (Cart cart : carts) {
            if (cart.moved) continue;
            if (cart.position.y < top.position.y) top = cart;
            if (cart.position.y == top.position.y) {
                if (cart.position.x < top.position.x) top = cart;
            }
        }
        return top;
    }

    private void init() {
        for (int y = 0; y < s.length; y++) {
            List<Character> row = new LinkedList<>();
            for (int x = 0; x < s[y].length(); x++) {
                if (s[y].charAt(x) == '<' || s[y].charAt(x) == '>') {
                    row.add('-');
                    carts.add(new Cart(new Point(x, y), s[y].charAt(x)));
                } else if (s[y].charAt(x) == '^' || s[y].charAt(x) == 'v') {
                    row.add('|');
                    carts.add(new Cart(new Point(x, y), s[y].charAt(x)));
                } else
                    row.add(s[y].charAt(x));
            }
            map.add(row);
        }
    }

    private char getNextPath(Cart c) {
        int x = c.position.x + c.direction.x;
        int y = c.position.y + c.direction.y;
        return map.get(y).get(x);
    }

    private class Cart {

        Point position;
        boolean moved;
        boolean crash;
        Point direction;

        // 0 == left
        // 1 == straight
        // 2 == right
        int nextTurn;

        public Cart(Point position, char direction) {
            this.position = position;
            moved = false;
            crash = false;
            switch (direction){
                case '<' : this.direction = new Point(-1, 0); break;
                case '^' : this.direction = new Point(0, -1); break;
                case '>' : this.direction = new Point(1, 0); break;
                case 'v' : this.direction = new Point(0, 1); break;
            }
            nextTurn = 0;
        }

        public void move() {
            position.x += direction.x;
            position.y += direction.y;
        }

        public void turn(char c) {
            if (c == '/') {
                if (direction.x == 0) {
                    direction = new Point(-direction.y, 0);
                } else if (direction.y == 0) {
                    direction = new Point(0, -direction.x);
                }
            } else if (c == '\\') {
                if (direction.x == 0) {
                    direction = new Point(direction.y, 0);
                } else if (direction.y == 0) {
                    direction = new Point(0, direction.x);
                }
            } else {
                junction();
            }
        }

        public void junction() {
            if (nextTurn == 0) {
                if (direction.x == 0)
                    direction = new Point(direction.y, 0);
                else if (direction.y == 0)
                    direction = new Point(0, -direction.x);
            } else if (nextTurn == 2) {
                if (direction.x == 0)
                    direction = new Point(-direction.y, 0);
                else if (direction.y == 0)
                    direction = new Point(0, direction.x);
            }
            nextTurn = (nextTurn + 1) % 3;
        }
    }
}

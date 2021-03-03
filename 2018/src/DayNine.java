import java.util.ArrayDeque;

/**
 * Created by JakobErlandsson on 2018-12-16.
 */
public class DayNine {

    static void dayNine() {
        long[] players = new long[464];
        int marbles = 70918 * 100;
        CircleDeque<Integer> circle = new CircleDeque<>();
        circle.addFirst(0);
        for (int i = 1; i <= marbles; i++) {
            if (i % 23 == 0) {
                circle.rotate(-7);
                players[i % players.length] += i + circle.pop();
            } else {
                circle.rotate(2);
                circle.addLast(i);
            }
        }
        long highest = 0;
        for (long i : players) {
            if (i > highest) highest = i;
        }
        System.out.println(highest);
    }

    static class CircleDeque<T> extends ArrayDeque<T> {
        void rotate(int num) {
            if (num == 0) return;
            if (num > 0) {
                for (int i = 0; i < num; i++) {
                    T j = this.removeLast();
                    this.addFirst(j);
                }
            } else {
                for (int i = 0; i < Math.abs(num) - 1; i++) {
                    T j = this.remove();
                    this.addLast(j);
                }
            }

        }
    }
}

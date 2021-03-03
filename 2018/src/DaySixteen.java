import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;

/**
 * Created by JakobErlandsson on 2018-12-19.
 */
public class DaySixteen {

    public DaySixteen() {
        daySixteen();
    }

    String input = FileHandler.readFile("16.txt");
    String[] s = input.split("\n");
    int[] registers = new int[4];
    HashMap<Integer, Runnable> operations = new HashMap<>();
    List<Runnable> functions = new LinkedList<>();

    int count = 0;
    int a;
    int b;
    int c;

    void daySixteen() {
        init();

        while (operations.size() < 16) {
            for (int i = 0; i < 3184; i += 4) {
                int[] before = toIntArray(s[i].toCharArray());
                int[] instruction = toIntArray(s[i + 1].toCharArray());
                int[] after = toIntArray(s[i + 2].toCharArray());
                List<Runnable> matches = new LinkedList<>();

                for (Runnable r : functions) {
                    setRegister(before);
                    a = instruction[1];
                    b = instruction[2];
                    c = instruction[3];
                    r.run();
                    if (matching(after)) matches.add(r);
                }
                if (matches.size() == 1) {
                    operations.put(instruction[0], matches.get(0));
                    functions.remove(matches.get(0));
                }
            }
        }
        registers = new int[4];
        for(int i = 3186; i < s.length; i++){
            int[] inst = toIntArray(s[i].toCharArray());
            a = inst[1];
            b = inst[2];
            c = inst[3];
            operations.get(inst[0]).run();
        }
        System.out.println(registers[0]);
    }

    void init() {
        functions.add(this::addr);
        functions.add(this::addi);
        functions.add(this::mulr);
        functions.add(this::muli);
        functions.add(this::banr);
        functions.add(this::bani);
        functions.add(this::borr);
        functions.add(this::bori);
        functions.add(this::setr);
        functions.add(this::seti);
        functions.add(this::gtir);
        functions.add(this::gtri);
        functions.add(this::gtrr);
        functions.add(this::eqir);
        functions.add(this::eqri);
        functions.add(this::eqrr);
    }

    void setRegister(int[] a) {
        for (int i = 0; i < a.length; i++) {
            registers[i] = a[i];
        }
    }

    boolean matching(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (registers[i] != a[i]) return false;
        }
        return true;
    }

    int[] toIntArray(char[] a) {
        int[] tmp = new int[4];
        if (a.length == 4) {
            for (int i = 0; i < a.length; i++) {
                tmp[i] = Character.getNumericValue(a[i]);
            }
        } else if (a.length == 5) {
            for (int i = 2; i < a.length; i++) {
                tmp[i - 1] = Character.getNumericValue(a[i]);
            }
            tmp[0] = Character.getNumericValue(a[1]) + Character.getNumericValue(a[0]) * 10;
        } else if (a.length > 5) {
            int index = 3;
            for (int i = 2; i <= 8; i += 2) {
                tmp[index] = Character.getNumericValue(a[a.length - i]);
                index--;
            }
        }
        return tmp;
    }

    void addr() {
        registers[c] = registers[a] + registers[b];
    }

    void addi() {
        registers[c] = registers[a] + b;
    }

    void mulr() {
        registers[c] = registers[a] * registers[b];
    }

    void muli() {
        registers[c] = registers[a] * b;
    }

    void banr() {
        registers[c] = registers[a] & registers[b];
    }

    void bani() {
        registers[c] = registers[a] & b;
    }

    void borr() {
        registers[c] = registers[a] | registers[b];
    }

    void bori() {
        registers[c] = registers[a] | b;
    }

    void setr() {
        registers[c] = registers[a];
    }

    void seti() {
        registers[c] = a;
    }

    void gtir() {
        registers[c] = a > registers[b] ? 1 : 0;
    }

    void gtri() {
        registers[c] = registers[a] > b ? 1 : 0;
    }

    void gtrr() {
        registers[c] = registers[a] > registers[b] ? 1 : 0;
    }

    void eqir() {
        registers[c] = a == registers[b] ? 1 : 0;
    }

    void eqri() {
        registers[c] = registers[a] == b ? 1 : 0;
    }

    void eqrr() {
        registers[c] = registers[a] == registers[b] ? 1 : 0;
    }


}

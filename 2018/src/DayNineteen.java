import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by JakobErlandsson on 2018-12-21.
 */
public class DayNineteen {

    String[] input = FileHandler.readFile("19.txt").split("\n");
    int[] registers = new int[6];
    int ip = 0;
    int a, b, c;
    int r3;
    HashMap<String, Runnable> functions = new HashMap<>();

    DayNineteen(){
        dayNineteen();
    }

    void dayNineteen(){
        //init();

        /*while(ip < input.length){
            int[] instructions = getInstructions(input[ip]);
            String op = input[ip].substring(0,4);
            a = instructions[0];
            b = instructions[1];
            c = instructions[2];
            functions.get(op).run();
            registers[1]++;
            ip = registers[1];
        }
        System.out.println("Problem 1: " + registers[0]);*/

        int r0 = 0;
        r3 = 920;
        for(int i = 1; i <= r3; i++){
            for(int j = 1; j <=r3; j++){
                if(i*j > r3) break;
                if(i*j == r3) r0 += i;
            }
        }
        System.out.println("Problem 1: " + r0);

        int r3 = 10551320;
        r0 = 0;

        for(int r5 = 1; r5 <= r3; r5++){
            for(int r2 = 1; r2 <= r3; r2++){
                if(r5 * r2 > r3) break;
                if(r5 * r2 == r3) r0 += r5;
            }
        }
        System.out.println("Problem 2: " + r0);
    }

    int[] getInstructions(String s){
        int[] tmp = new int[3];
        tmp[2] = Character.getNumericValue(s.charAt(s.length()-1));
        tmp[0] = Character.getNumericValue(s.charAt(5));
        if(s.length() > 10){
            tmp[1] = Character.getNumericValue(s.charAt(8)) + Character.getNumericValue(s.charAt(7)) * 10;
        }
        else tmp[1] = Character.getNumericValue(s.charAt(7));
        return tmp;
    }

    void init() {
        functions.put("addr",this::addr);
        functions.put("addi",this::addi);
        functions.put("mulr",this::mulr);
        functions.put("muli",this::muli);
        functions.put("banr",this::banr);
        functions.put("bani",this::bani);
        functions.put("borr",this::borr);
        functions.put("bori",this::bori);
        functions.put("setr",this::setr);
        functions.put("seti",this::seti);
        functions.put("gtir",this::gtir);
        functions.put("gtri",this::gtri);
        functions.put("gtrr",this::gtrr);
        functions.put("eqir",this::eqir);
        functions.put("eqri",this::eqri);
        functions.put("eqrr",this::eqrr);
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

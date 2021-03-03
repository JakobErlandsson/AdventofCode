import java.util.*;

/**
 * Created by JakobErlandsson on 2018-12-16.
 */
public class DaySeven {

    static void daySevenPart1(String input) {
        HashMap<Character, List<Character>> requirements = new HashMap<>();
        String[] s = input.split("\n");
        List<Character> keys = new ArrayList<>();
        for (String each : s) {
            char node = each.charAt(36);
            if (!keys.contains(node)) keys.add(node);
            char req = each.charAt(5);
            if (!keys.contains(req)) keys.add(req);
            if (!requirements.containsKey(node)) {
                requirements.put(node, new ArrayList<>());
            }
            requirements.get(node).add(req);
        }

        char[] arr = new char[keys.size()];
        Iterator it = keys.iterator();
        int index = 0;
        while (it.hasNext()) {
            arr[index] = (char) it.next();
            index++;
        }
        Arrays.sort(arr);
        boolean[] visited = new boolean[arr.length];
        StringBuilder sb = new StringBuilder();

        while (sb.length() < arr.length) {
            for (int i = 0; i < arr.length; i++) {
                if (visited[i]) continue;
                if (requirements.get(arr[i]) == null || (Helper.isDone(requirements.get(arr[i]), arr, visited))) {
                    sb.append(arr[i]);
                    visited[i] = true;
                    break;
                }
            }
        }
        System.out.println("Day seven part one: " + sb.toString());
    }

    static void daySevenPart2(String input) {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        List<Node> letters = new ArrayList<Node>();
        for (char c : alphabet) {
            letters.add(new Node(c));
        }
        String[] s = input.split("\n");
        for (String each : s) {
            char node = each.charAt(36);
            char req = each.charAt(5);
            Node n1 = letters.get(Helper.indexOf(alphabet, node));
            Node n2 = letters.get(Helper.indexOf(alphabet, req));
            n1.required.add(n2);

        }
        Node[] workingOn = new Node[5];
        StringBuilder sb = new StringBuilder();
        int[] seconds = new int[alphabet.length];
        for (int i = 0; i < seconds.length; i++) {
            seconds[i] = 60 + i + 1;
        }
        int time = 0;

        while (sb.length() < alphabet.length) {
            Node first = getFirstAvailable(letters, alphabet);
            if (workerAvailable(workingOn) && first != null) {
                int worker = getWorker(workingOn);
                workingOn[worker] = first;
                first.timeLeft = seconds[Helper.indexOf(alphabet, first.name)];
            } else {
                int shortest = getShortestRemaining(workingOn);
                for (int i = 0; i < workingOn.length; i++) {
                    if (workingOn[i] != null) {
                        workingOn[i].timeLeft -= shortest;
                        if (workingOn[i].timeLeft == 0) {
                            sb.append(workingOn[i].name);
                            workingOn[i].visited = true;
                            workingOn[i] = null;
                        }
                    }
                }
                time += shortest;
            }
        }
        System.out.println("Day 7 part two: " + time + " with " + sb.toString());
    }

    public static class Node{

        char name;
        boolean visited;
        List<Node> required;
        int timeLeft;

        public Node(char name) {
            this.name = name;
            this.visited = visited;
            required = new ArrayList<>();
            timeLeft = 0;
        }

        public boolean isReady(){
            for(Node n : required){
                if(!n.visited) return false;
            }
            return !visited && timeLeft == 0;
        }
    }

    static boolean workerAvailable(Node[] narr) {
        for (Node n : narr) {
            if (n == null) return true;
        }
        return false;
    }

    static int getWorker(Node[] narr) {
        for (int i = 0; i < narr.length; i++) {
            if (narr[i] == null) return i;
        }
        return -1;
    }

    static Node getFirstAvailable(List<Node> l, char[] a) {
        for (int i = 0; i < a.length; i++) {
            if (l.get(i).isReady()) return l.get(i);
        }
        return null;
    }

    static int getShortestRemaining(Node[] narr) {
        int shortest = 1000000;
        for (Node n : narr) {
            if (n != null)
                if (n.timeLeft < shortest) shortest = n.timeLeft;
        }
        return shortest;
    }
}

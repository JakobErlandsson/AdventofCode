/**
 * Created by JakobErlandsson on 2018-12-20.
 */
public class DayEighteen {

    DayEighteen() {
        dayEighteen();
    }

    String[] input = FileHandler.readFile("18.txt").split("\n");
    char[][] map = new char[input.length][input.length];
    int diff = 0;
    int result = 0;

    void dayEighteen() {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                map[i][j] = input[i].charAt(j);
            }
        }
        char[][] nextMap = new char[map.length][map.length];
        for (int n = 1; n <= 2000; n++) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    if(map[i][j] == '.'){
                        if(nTrees(i, j) >= 3) nextMap[i][j] = '|';
                        else nextMap[i][j] = '.';
                    }
                    else if(map[i][j] == '|'){
                        if(nYards(i, j) >= 3) nextMap[i][j] = '#';
                        else nextMap[i][j] = '|';
                    }
                    else{
                        if(nYards(i, j) >= 1 && nTrees(i, j) >= 1) nextMap[i][j] = '#';
                        else nextMap[i][j] = '.';
                    }
                }
            }
            for(int i = 0; i < map.length; i++){
                for(int j = 0; j < map.length; j++){
                    map[i][j] = nextMap[i][j];
                }
            }
            int trees = 0;
            int yards = 0;
            for(int i = 0; i < map.length; i++){
                for(int j = 0; j < map.length; j++){
                    if(map[i][j] == '|') trees++;
                    if(map[i][j] == '#') yards++;
                }
            }
            result = trees * yards;
            System.out.println(n + ":\t" + result);
        }
    }

    int nTrees(int x, int y) {
        int count = 0;
        if (x == 0 && y == 0) {
            if (map[x + 1][y] == '|') count++;
            if (map[x + 1][y + 1] == '|') count++;
            if (map[x][y + 1] == '|') count++;

        } else if (x == map.length - 1 && y == 0) {
            if (map[x - 1][y] == '|') count++;
            if (map[x - 1][y + 1] == '|') count++;
            if (map[x][y + 1] == '|') count++;

        } else if (x == 0 && y == map.length - 1) {
            if (map[x + 1][y] == '|') count++;
            if (map[x + 1][y - 1] == '|') count++;
            if (map[x][y - 1] == '|') count++;

        } else if (x == map.length - 1 && y == map.length - 1) {
            if (map[x - 1][y] == '|') count++;
            if (map[x - 1][y - 1] == '|') count++;
            if (map[x][y - 1] == '|') count++;

        } else if (x == 0) {
            if (map[x][y - 1] == '|') count++;
            if (map[x + 1][y - 1] == '|') count++;
            if (map[x + 1][y] == '|') count++;
            if (map[x + 1][y + 1] == '|') count++;
            if (map[x][y + 1] == '|') count++;

        } else if (x == map.length - 1) {
            if (map[x][y - 1] == '|') count++;
            if (map[x - 1][y - 1] == '|') count++;
            if (map[x - 1][y] == '|') count++;
            if (map[x - 1][y + 1] == '|') count++;
            if (map[x][y + 1] == '|') count++;

        } else if (y == 0) {
            if (map[x - 1][y] == '|') count++;
            if (map[x - 1][y + 1] == '|') count++;
            if (map[x][y + 1] == '|') count++;
            if (map[x + 1][y + 1] == '|') count++;
            if (map[x + 1][y] == '|') count++;

        } else if (y == map.length - 1) {
            if (map[x - 1][y] == '|') count++;
            if (map[x - 1][y - 1] == '|') count++;
            if (map[x][y - 1] == '|') count++;
            if (map[x + 1][y - 1] == '|') count++;
            if (map[x + 1][y] == '|') count++;

        } else {
            if (map[x - 1][y - 1] == '|') count++;
            if (map[x][y - 1] == '|') count++;
            if (map[x + 1][y - 1] == '|') count++;
            if (map[x + 1][y] == '|') count++;
            if (map[x + 1][y + 1] == '|') count++;
            if (map[x][y + 1] == '|') count++;
            if (map[x - 1][y + 1] == '|') count++;
            if (map[x - 1][y] == '|') count++;
        }
        return count;
    }

    int nYards(int x, int y) {
        int count = 0;
        if (x == 0 && y == 0) {
            if (map[x + 1][y] == '#') count++;
            if (map[x + 1][y + 1] == '#') count++;
            if (map[x][y + 1] == '#') count++;
        } else if (x == map.length - 1 && y == 0) {
            if (map[x - 1][y] == '#') count++;
            if (map[x - 1][y + 1] == '#') count++;
            if (map[x][y + 1] == '#') count++;
        } else if (x == 0 && y == map.length - 1) {
            if (map[x + 1][y] == '#') count++;
            if (map[x + 1][y - 1] == '#') count++;
            if (map[x][y - 1] == '#') count++;
        } else if (x == map.length - 1 && y == map.length - 1) {
            if (map[x - 1][y] == '#') count++;
            if (map[x - 1][y - 1] == '#') count++;
            if (map[x][y - 1] == '#') count++;
        } else if (x == 0) {
            if (map[x][y - 1] == '#') count++;
            if (map[x + 1][y - 1] == '#') count++;
            if (map[x + 1][y] == '#') count++;
            if (map[x + 1][y + 1] == '#') count++;
            if (map[x][y + 1] == '#') count++;
        } else if (x == map.length - 1) {
            if (map[x][y - 1] == '#') count++;
            if (map[x - 1][y - 1] == '#') count++;
            if (map[x - 1][y] == '#') count++;
            if (map[x - 1][y + 1] == '#') count++;
            if (map[x][y + 1] == '#') count++;
        } else if (y == 0) {
            if (map[x - 1][y] == '#') count++;
            if (map[x - 1][y + 1] == '#') count++;
            if (map[x][y + 1] == '#') count++;
            if (map[x + 1][y + 1] == '#') count++;
            if (map[x + 1][y] == '#') count++;
        } else if (y == map.length - 1) {
            if (map[x - 1][y] == '#') count++;
            if (map[x - 1][y - 1] == '#') count++;
            if (map[x][y - 1] == '#') count++;
            if (map[x + 1][y - 1] == '#') count++;
            if (map[x + 1][y] == '#') count++;
        } else {
            if (map[x - 1][y - 1] == '#') count++;
            if (map[x][y - 1] == '#') count++;
            if (map[x + 1][y - 1] == '#') count++;
            if (map[x + 1][y] == '#') count++;
            if (map[x + 1][y + 1] == '#') count++;
            if (map[x][y + 1] == '#') count++;
            if (map[x - 1][y + 1] == '#') count++;
            if (map[x - 1][y] == '#') count++;
        }
        return count;
    }

}

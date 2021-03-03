/**
 * Created by JakobErlandsson on 2018-12-16.
 */
public class DayEleven {

    static void dayEleven() {
        long time = System.currentTimeMillis();
        int gridSerialNumber = 8772;
        int[][] grid = new int[300][300];
        int rackID;
        int powerLevel;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                rackID = x + 1 + 10;
                powerLevel = rackID * (y + 1);
                powerLevel += gridSerialNumber;
                powerLevel *= rackID;
                powerLevel = getHundredsDigit(powerLevel);
                powerLevel -= 5;
                grid[x][y] = powerLevel;
            }
        }
        int largest = 0;
        int lX = 0;
        int lY = 0;
        int biggestIndex = 0;
        for (int i = 300; i > 0; i--) {
            for (int x = 0; x < grid.length - i; x++) {
                for (int y = 0; y < grid.length - i; y++) {
                    int totalPower = getTotalPower(x, y, grid, i);
                    if (totalPower > largest) {
                        largest = totalPower;
                        lX = x;
                        lY = y;
                        biggestIndex = i;

                    }
                }
            }
        }
        System.out.println("Part two: " + (lX + 1) + "," + (lY + 1) + "," + biggestIndex);

    }

    static int getTotalPower(int x, int y, int[][] grid, int n) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result += grid[x + i][y + j];
            }
        }
        return result;
    }

    static int getHundredsDigit(int n) {
        return n < 100 ? 0 : (n % 1000) / 100;
    }
}

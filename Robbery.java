package saakshiapplication;

abstract class Robber {
    public void RobbingClass() {
        System.out.println("MScAI&ML");
    }

    void MachineLearning() {
        System.out.println("I love Machine Learning");
    }
}

class JAVAProfessionalRobber extends Robber {
    int RowHouses(int[] m) {
    	//RowHouses(): Accepts an integer array representing the amount of money in each
    	//rowhouse. The objective is to maximize the amount robbed without triggering
    	//security alarms from adjacent houses. The method should return the maximum
    	//amount that can be robbed without getting caught.
        int n = m.length;
        if (n == 0) return 0;
        if (n == 1) return m[0];
        int pmax = 0, cmax = 0;

        for (int i = 0; i < n; i++) {
            int currentHouseMoney = m[i];
            int newMax = Math.max(cmax, pmax + currentHouseMoney);
            pmax = cmax;
            cmax = newMax;
        }

        return cmax;
    }

    int RoundHouses(int[] m) {
    	//RoundHouses(): Accepts an integer array representing the amount of money in each
    	//roundhouse. Since the houses form a circle, with the last and first houses being
    	//adjacent, the goal is to maximize the amount robbed without triggering security
    	//alarms from adjacent houses. The method
        int n = m.length;
        if (n == 0) return 0;
        if (n == 1) return m[0];
        int maxRobbingExclFirst = robLinear(m, 1, n - 1);
        int maxRobbingExclLast = robLinear(m, 0, n - 2);
        return Math.max(maxRobbingExclFirst, maxRobbingExclLast);
    }

    private int robLinear(int[] money, int start, int end) {
        int previousMax = 0, currentMax = 0;
        for (int i = start; i <= end; i++) {
            int currentHouseMoney = money[i];
            int newMax = Math.max(currentMax, previousMax + currentHouseMoney);
            previousMax = currentMax;
            currentMax = newMax;
        }
        return currentMax;
    }

    int SquareHouses(int[] m) {
    	//can be robbed without getting caught. (2 Marks)
    	//● SquareHouse(): Accepts an integer array representing the amount of money in each
    	//square house. Robbing adjacent square houses triggers security alarms. The method
    	//should return the maximum amount that can be robbed without getting caught.
        int prev1 = 0, prev2 = 0;
        for (int value : m) {
            int current = Math.max(prev2, prev1 + value);
            prev1 = prev2;
            prev2 = current;
        }
        return prev2;
    }

    public int MuliHouseBuilding(int[][] money) {
        // MuliHouseBuilding(): Accepts an integer array representing the amount of money in
        // each type of house in a multi-type building. Robbing adjacent houses of any type
        // triggers security alarms. The method should return the maximum amount that can be
        // robbed without getting caught.

        int odd = 0;
        int even = 0;
        for (int i = 0; i < money.length; i++) {  
            if (i % 2 == 0) {
                for (int j = 0; j < money[i].length; j++) {
                    even += money[i][j];
                }
            }
            else {
                for (int j = 0; j < money[i].length; j++) {
                    odd += money[i][j];
                }
            }
        }
        return Math.max(odd, even);
    }
}

public class Robbery {
    public static void main(String[] args) {
        int row, round, square, multi;
        JAVAProfessionalRobber ob = new JAVAProfessionalRobber();
        ob.RobbingClass();
        ob.MachineLearning();
        row = ob.RowHouses(new int[]{1, 2, 3, 0});
        round = ob.RoundHouses(new int[]{1, 2, 3, 4});
        square = ob.SquareHouses(new int[]{5, 10, 2, 7});
        multi = ob.MuliHouseBuilding(new int[][]{
            {5, 3, 8, 2},
            {10, 12, 7, 6},
            {4, 9, 11, 5},
            {8, 6, 3, 7}
        });

        System.out.println("Max amount robbed from Row House is: " + row);
        System.out.println("Max amount robbed from Round House is: " + round);
        System.out.println("Max amount robbed from Square House is: " + square);
        System.out.println("Max amount robbed from Multi House is: " + multi);
    }
}

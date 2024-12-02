package saakshiapplication;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoinChange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User input for the number of coins
        System.out.print("Enter the number of coins: ");
        int n = scanner.nextInt();

        // User input for coin denominations
        int[] coins = new int[n];
        System.out.println("Enter the coin denominations:");
        for (int i = 0; i < n; i++) {
            coins[i] = scanner.nextInt();
        }

        // User input for the desired sum
        System.out.print("Enter the desired sum: ");
        int sum = scanner.nextInt();

        // Calculate and print combinations
        List<List<Integer>> combinations = calculateCombinations(coins, sum);
        System.out.println("Number of ways to make the sum: " + combinations.size());
        System.out.println("Possible combinations:");
        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }

        scanner.close();
    }

    public static List<List<Integer>> calculateCombinations(int[] coins, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        findCombinations(coins, sum, 0, new ArrayList<>(), result);
        return result;
    }

    private static void findCombinations(int[] coins, int remaining, int startIndex, List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            // Found a valid combination
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = startIndex; i < coins.length; i++) {
            if (coins[i] <= remaining) {
                // Include the current coin
                current.add(coins[i]);
                findCombinations(coins, remaining - coins[i], i, current, result); // Ensure only coins >= current are considered
                // Backtrack
                current.remove(current.size() - 1);
            }
        }
    }
}

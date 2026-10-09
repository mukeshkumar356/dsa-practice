/**
 * Given an array of coin denominations and a target amount, return the fewest
 * number of coins needed to make up that amount, or -1 if it is impossible.
 * Each coin can be used an unlimited number of times.
 *
 * Approach: bottom-up dynamic programming (unbounded knapsack).
 * dp[a] = minimum coins needed to make amount a.
 *
 * - dp[0] = 0 (no coins needed for amount 0)
 * - For each amount a, try every coin c <= a: dp[a] = min(dp[a], dp[a - c] + 1)
 * - Unreachable amounts stay at a sentinel (amount + 1, larger than any
 *   valid answer); if dp[amount] is still the sentinel, return -1.
 *
 * Time complexity:  O(amount * n) where n = number of coins
 * Space complexity: O(amount) - the dp array
 */
import java.util.Arrays;

public class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int a = 1; a <= amount; a++) {
            for (int c : coins) {
                if (c <= a) {
                    dp[a] = Math.min(dp[a], dp[a - c] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.coinChange(new int[]{1, 2, 5}, 11)); // 3 (5+5+1)
        System.out.println(s.coinChange(new int[]{2}, 3));        // -1
        System.out.println(s.coinChange(new int[]{1}, 0));        // 0 (edge: amount 0)
        System.out.println(s.coinChange(new int[]{186, 419, 83, 408}, 6249)); // 20
    }
}

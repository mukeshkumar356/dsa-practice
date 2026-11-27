/**
 * Partition Equal Subset Sum: given an array of positive integers nums,
 * return true if it can be split into two subsets with equal sum.
 *
 * Approach: the total must be even; then the question becomes "is there a
 * subset summing to total/2?" - a 0/1 knapsack. dp[s] = true if some subset
 * of the numbers seen so far sums to s. For each num, update s from target
 * down to num (descending so each number is used at most once):
 * dp[s] |= dp[s - num].
 *
 * Time complexity:  O(n * target), target = sum/2
 * Space complexity: O(target) - single boolean array
 */
public class Solution {
    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int n : nums) total += n;
        if (total % 2 != 0) return false;   // odd sum can never split evenly
        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;                        // empty subset sums to 0
        for (int num : nums) {
            for (int s = target; s >= num; s--) {
                if (dp[s - num]) dp[s] = true;
            }
            if (dp[target]) return true;     // early exit
        }
        return dp[target];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] tests = {
            {1, 5, 11, 5},     // true  ([1,5,5] and [11])
            {1, 2, 3, 5},      // false (sum 11 is odd)
            {1},               // false (single element)
            {2, 2, 3, 5},      // false (sum 12, no subset of 6)
            {100, 100},        // true
            {1, 2, 5},         // false (sum 8, target 4 unreachable)
        };
        boolean[] expected = {true, false, false, false, true, false};
        for (int i = 0; i < tests.length; i++) {
            boolean got = s.canPartition(tests[i]);
            System.out.println((got == expected[i] ? "PASS" : "FAIL")
                + " got=" + got + " expected=" + expected[i]);
        }
    }
}

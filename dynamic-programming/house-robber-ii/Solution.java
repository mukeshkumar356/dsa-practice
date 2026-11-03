/**
 * House Robber II: Houses are arranged in a circle, each holding some money.
 * You cannot rob two adjacent houses (the first and last are adjacent).
 * Return the maximum amount you can rob.
 *
 * Approach: Dynamic programming. Because house 0 and house n-1 are adjacent,
 * at most one of them can be robbed. Solve the linear House Robber problem
 * twice - once on nums[0..n-2] and once on nums[1..n-1] - and take the max.
 * The linear problem keeps two rolling values: best total ending before the
 * previous house (prev2) and before the current house (prev1), with
 * cur = max(prev1, prev2 + money).
 *
 * Time complexity:  O(n) - two linear passes
 * Space complexity: O(1) - only a few rolling variables
 */
public class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        return Math.max(robLinear(nums, 0, n - 2), robLinear(nums, 1, n - 1));
    }

    // Classic House Robber on nums[lo..hi] inclusive.
    private int robLinear(int[] nums, int lo, int hi) {
        int prev2 = 0, prev1 = 0;
        for (int i = lo; i <= hi; i++) {
            int cur = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        check(s.rob(new int[]{2, 3, 2}), 3);
        check(s.rob(new int[]{1, 2, 3, 1}), 4);
        check(s.rob(new int[]{1, 2, 3}), 3);
        check(s.rob(new int[]{5}), 5);                  // single house
        check(s.rob(new int[]{2, 7, 9, 3, 1}), 11);
        check(s.rob(new int[]{200, 3, 140, 20, 10}), 340);
        System.out.println("All tests passed");
    }

    private static void check(int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError("expected " + expected + " but got " + actual);
        }
    }
}

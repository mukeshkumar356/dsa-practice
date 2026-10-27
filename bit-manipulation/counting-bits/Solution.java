import java.util.Arrays;

/**
 * Given a non-negative integer n, return an array ans of length n + 1 where
 * ans[i] is the number of 1 bits in the binary representation of i.
 *
 * Approach: dynamic programming on bits. Shifting i right by one drops its
 * lowest bit, giving i >> 1 (already computed since it is smaller). So
 * ans[i] = ans[i >> 1] + (i & 1): the count for the shifted number plus the
 * bit that was dropped.
 *
 * Time complexity:  O(n) - one O(1) step per number
 * Space complexity: O(1) extra - only the output array is allocated
 */
public class Solution {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1]; // ans[0] = 0 by default
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.countBits(5)));  // [0, 1, 1, 2, 1, 2]
        System.out.println(Arrays.toString(s.countBits(0)));  // [0] (edge case)
        System.out.println(Arrays.toString(s.countBits(16))); // [0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4, 1]
    }
}

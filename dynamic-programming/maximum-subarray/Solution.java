/**
 * Maximum Subarray: given an integer array nums, find the contiguous
 * subarray (containing at least one element) with the largest sum and
 * return that sum.
 *
 * Approach: Kadane's algorithm. Let best-ending-here be the max sum of a
 * subarray ending at index i. Either extend the previous subarray or start
 * fresh at nums[i]:  cur = max(nums[i], cur + nums[i]).
 * The answer is the maximum cur seen. Starting from nums[0] (not 0) keeps
 * all-negative arrays correct.
 *
 * Time complexity:  O(n) - single pass
 * Space complexity: O(1) - two running variables
 */
public class Solution {
    public int maxSubArray(int[] nums) {
        int cur = nums[0];
        int best = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // drop the running sum if it has become a burden
            cur = Math.max(nums[i], cur + nums[i]);
            best = Math.max(best, cur);
        }
        return best;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] tests = {
            {-2, 1, -3, 4, -1, 2, 1, -5, 4},  // 6  ([4,-1,2,1])
            {1},                              // 1
            {5, 4, -1, 7, 8},                 // 23
            {-3, -2, -5},                     // -2 (all negative)
            {-1, 2, -1, 2, -1},               // 3
        };
        int[] expected = {6, 1, 23, -2, 3};
        for (int i = 0; i < tests.length; i++) {
            int got = s.maxSubArray(tests[i]);
            System.out.println((got == expected[i] ? "PASS" : "FAIL")
                + " got=" + got + " expected=" + expected[i]);
        }
    }
}

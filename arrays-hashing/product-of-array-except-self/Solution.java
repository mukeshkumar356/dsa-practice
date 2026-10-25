import java.util.Arrays;

/**
 * Product of Array Except Self: given an integer array nums, return an array
 * answer where answer[i] is the product of every element of nums except
 * nums[i]. Must run in O(n) without using division.
 *
 * Approach: two passes using the output array. The first pass (left to right)
 * stores in answer[i] the product of all elements to the left of i. The second
 * pass (right to left) keeps a running product of everything to the right of i
 * and multiplies it into answer[i]. Works with zeros since no division is used.
 *
 * Time complexity:  O(n) - two linear passes
 * Space complexity: O(1) extra - only the output array and one running variable
 */
public class Solution {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        int left = 1;
        for (int i = 0; i < n; i++) {
            answer[i] = left;
            left *= nums[i];
        }

        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= right;
            right *= nums[i];
        }
        return answer;
    }

    private static void check(int[] nums, int[] expected) {
        int[] got = new Solution().productExceptSelf(nums);
        boolean ok = Arrays.equals(got, expected);
        System.out.println((ok ? "PASS " : "FAIL ") + Arrays.toString(nums)
                + " -> " + Arrays.toString(got));
        if (!ok) {
            throw new AssertionError("expected " + Arrays.toString(expected));
        }
    }

    public static void main(String[] args) {
        check(new int[]{1, 2, 3, 4}, new int[]{24, 12, 8, 6});
        check(new int[]{-1, 1, 0, -3, 3}, new int[]{0, 0, 9, 0, 0});  // one zero
        check(new int[]{0, 0, 2}, new int[]{0, 0, 0});                // two zeros
        check(new int[]{-2, 3}, new int[]{3, -2});                    // negatives, n = 2
        check(new int[]{5}, new int[]{1});                            // single element
    }
}

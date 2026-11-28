/**
 * Given an array nums containing n distinct numbers taken from the range
 * [0, n], return the one number in that range that is missing from the array.
 *
 * Approach: XOR trick. XOR-ing a value with itself gives 0 and XOR is
 * commutative/associative, so XOR-ing every index 0..n with every element
 * cancels all numbers that are present, leaving only the missing one.
 * (Start with x = n, then XOR in i and nums[i] for each i in [0, n).)
 * No sum is used, so there is no risk of integer overflow.
 *
 * Time complexity:  O(n)
 * Space complexity: O(1)
 */
public class Solution {
    public int missingNumber(int[] nums) {
        int x = nums.length;              // the index n has no matching slot in the loop
        for (int i = 0; i < nums.length; i++) {
            x ^= i ^ nums[i];             // present values cancel against their index
        }
        return x;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] tests = {{3, 0, 1}, {0, 1}, {9, 6, 4, 2, 3, 5, 7, 0, 1}, {0}, {1}, {}};
        int[] expected = {2, 2, 8, 1, 0, 0};
        boolean ok = true;
        for (int i = 0; i < tests.length; i++) {
            int got = s.missingNumber(tests[i]);
            System.out.println("test " + i + ": got " + got + ", expected " + expected[i]
                    + (got == expected[i] ? " OK" : " FAIL"));
            ok &= got == expected[i];
        }
        System.out.println(ok ? "ALL PASSED" : "SOME FAILED");
    }
}

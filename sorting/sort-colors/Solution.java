import java.util.Arrays;

/**
 * Given an array nums with n objects colored red, white, or blue (encoded as
 * 0, 1, 2), sort them in-place so same colors are adjacent in the order
 * 0, 1, 2, without using a library sort.
 *
 * Approach: Dutch National Flag (three-way partition) with three pointers.
 * lo = next slot for a 0, hi = next slot for a 2, i = scanning index.
 *  - nums[i] == 0: swap with lo, advance both lo and i.
 *  - nums[i] == 2: swap with hi, shrink hi (do NOT advance i, the swapped-in
 *    value is unexamined).
 *  - nums[i] == 1: just advance i.
 * Invariant: [0,lo) are 0s, [lo,i) are 1s, (hi,n-1] are 2s.
 *
 * Time complexity:  O(n) - single pass
 * Space complexity: O(1) - in-place
 */
public class Solution {
    public void sortColors(int[] nums) {
        int lo = 0, i = 0, hi = nums.length - 1;
        while (i <= hi) {
            if (nums[i] == 0) swap(nums, i++, lo++);
            else if (nums[i] == 2) swap(nums, i, hi--);
            else i++;
        }
    }

    private void swap(int[] a, int x, int y) {
        int t = a[x]; a[x] = a[y]; a[y] = t;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] tests = {{2,0,2,1,1,0}, {2,0,1}, {0}, {1,1,1}, {2,2,0,0}, {}};
        int[][] expected = {{0,0,1,1,2,2}, {0,1,2}, {0}, {1,1,1}, {0,0,2,2}, {}};
        boolean ok = true;
        for (int t = 0; t < tests.length; t++) {
            s.sortColors(tests[t]);
            boolean pass = Arrays.equals(tests[t], expected[t]);
            ok &= pass;
            System.out.println(Arrays.toString(tests[t]) + " " + (pass ? "PASS" : "FAIL"));
        }
        System.out.println(ok ? "ALL PASS" : "SOME FAILED");
    }
}

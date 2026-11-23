import java.util.*;

/**
 * Given an integer array nums, return all unique triplets [a, b, c] such that
 * a + b + c == 0. The solution set must not contain duplicate triplets.
 *
 * Approach: sort the array, then fix the first element i and use two pointers
 * (lo, hi) over the remainder to find pairs summing to -nums[i]. If the sum is
 * too small move lo right, too large move hi left. Duplicates are skipped for
 * i, lo and hi so each triplet appears once. Since the array is sorted, once
 * nums[i] > 0 no triplet can sum to zero, so we stop early.
 *
 * Time complexity:  O(n^2) - O(n log n) sort + O(n) two-pointer scan per i
 * Space complexity: O(1) extra (ignoring output and sort internals)
 */
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int[] a = nums.clone();
        Arrays.sort(a);
        int n = a.length;

        for (int i = 0; i < n - 2 && a[i] <= 0; i++) {
            if (i > 0 && a[i] == a[i - 1]) continue; // skip duplicate first element
            int lo = i + 1, hi = n - 1;
            while (lo < hi) {
                int sum = a[i] + a[lo] + a[hi];
                if (sum < 0) {
                    lo++;
                } else if (sum > 0) {
                    hi--;
                } else {
                    res.add(Arrays.asList(a[i], a[lo], a[hi]));
                    lo++;
                    hi--;
                    while (lo < hi && a[lo] == a[lo - 1]) lo++; // skip duplicate second
                    while (lo < hi && a[hi] == a[hi + 1]) hi--; // skip duplicate third
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        check(s.threeSum(new int[]{-1, 0, 1, 2, -1, -4}), "[[-1, -1, 2], [-1, 0, 1]]");
        check(s.threeSum(new int[]{0, 1, 1}), "[]");
        check(s.threeSum(new int[]{0, 0, 0, 0}), "[[0, 0, 0]]");
        check(s.threeSum(new int[]{-2, 0, 0, 2, 2}), "[[-2, 0, 2]]");
        check(s.threeSum(new int[]{1, 2}), "[]");
        System.out.println("All tests passed");
    }

    private static void check(List<List<Integer>> got, String expected) {
        if (!got.toString().equals(expected)) {
            throw new AssertionError("expected " + expected + " but got " + got);
        }
        System.out.println("OK " + got);
    }
}

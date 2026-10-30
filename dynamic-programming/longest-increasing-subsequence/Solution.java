/**
 * Given an integer array nums, return the length of the longest strictly
 * increasing subsequence (elements need not be contiguous).
 *
 * Approach: patience sorting. Keep tails[i] = the smallest possible tail value
 * of an increasing subsequence of length i+1 seen so far. tails is always
 * sorted, so for each number we binary search the first tail >= x and
 * overwrite it (or append if x is larger than every tail). The final size of
 * tails is the LIS length. (tails is not itself the subsequence.)
 *
 * Time complexity:  O(n log n) - one binary search per element
 * Space complexity: O(n) - the tails array
 */
public class Solution {
    public static int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int lo = 0, hi = size; // find first index with tails[idx] >= x
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (tails[mid] < x) lo = mid + 1;
                else hi = mid;
            }
            tails[lo] = x;
            if (lo == size) size++;
        }
        return size;
    }

    public static void main(String[] args) {
        check(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}), 4);
        check(lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}), 4);
        check(lengthOfLIS(new int[]{7, 7, 7, 7}), 1);   // strictly increasing
        check(lengthOfLIS(new int[]{5}), 1);
        check(lengthOfLIS(new int[]{}), 0);
        check(lengthOfLIS(new int[]{5, 4, 3, 2, 1}), 1);
        check(lengthOfLIS(new int[]{1, 2, 3, 4, 5}), 5);
        System.out.println("All tests passed");
    }

    private static void check(int actual, int expected) {
        if (actual != expected) throw new AssertionError("expected " + expected + " got " + actual);
    }
}

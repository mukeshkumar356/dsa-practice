/**
 * Median of Two Sorted Arrays: given two sorted arrays nums1 and nums2, return the
 * median of the combined sorted data in O(log(min(m, n))) time.
 *
 * Approach: binary search on the partition of the smaller array. Choose i elements
 * from A (smaller) and j = (m + n + 1) / 2 - i from B for the left half. The
 * partition is valid when A[i-1] <= B[j] and B[j-1] <= A[i]. If A[i-1] > B[j], move
 * i left; otherwise move it right. Missing neighbours at the edges are treated as
 * -infinity / +infinity. For odd total the median is max(left side); for even it is
 * the average of max(left side) and min(right side).
 *
 * Time complexity:  O(log(min(m, n)))
 * Space complexity: O(1)
 */
public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
        int m = nums1.length, n = nums2.length;
        int lo = 0, hi = m, half = (m + n + 1) / 2;

        while (lo <= hi) {
            int i = (lo + hi) / 2;
            int j = half - i;
            int aLeft = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int aRight = i == m ? Integer.MAX_VALUE : nums1[i];
            int bLeft = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int bRight = j == n ? Integer.MAX_VALUE : nums2[j];

            if (aLeft <= bRight && bLeft <= aRight) {
                int maxLeft = Math.max(aLeft, bLeft);
                if ((m + n) % 2 == 1) return maxLeft;
                return (maxLeft + (double) Math.min(aRight, bRight)) / 2.0;
            } else if (aLeft > bRight) {
                hi = i - 1;
            } else {
                lo = i + 1;
            }
        }
        throw new IllegalArgumentException("Input arrays must be sorted");
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        check(sol.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}), 2.0);
        check(sol.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}), 2.5);
        check(sol.findMedianSortedArrays(new int[]{}, new int[]{1}), 1.0);          // edge: empty array
        check(sol.findMedianSortedArrays(new int[]{1, 2}, new int[]{10, 11}), 6.0); // edge: no overlap
        check(sol.findMedianSortedArrays(new int[]{0, 0}, new int[]{0, 0}), 0.0);   // duplicates
        System.out.println("All tests passed");
    }

    private static void check(double actual, double expected) {
        if (Math.abs(actual - expected) > 1e-9) {
            throw new AssertionError("expected " + expected + " but got " + actual);
        }
    }
}

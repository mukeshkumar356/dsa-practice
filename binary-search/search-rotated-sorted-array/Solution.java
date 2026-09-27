/**
 * Given a rotated sorted array (originally ascending, then rotated at some
 * unknown pivot) and a target value, return the index of the target, or -1
 * if it isn't present. Must run in O(log n).
 *
 * Approach: modified binary search. At every step, at least one half of the
 * array (left..mid or mid..right) is guaranteed to still be in properly
 * sorted order, even though the array as a whole is rotated. So:
 *  1. Find which half is sorted by comparing nums[left] and nums[mid].
 *  2. Check if target lies within that sorted half's range.
 *  3. If yes, search that half; if no, search the other half.
 *
 * Time complexity:  O(log n) - halves the search space each iteration
 * Space complexity: O(1) - iterative, no extra structures
 */
public class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return mid;

            boolean leftHalfSorted = nums[left] <= nums[mid];

            if (leftHalfSorted) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 0)); // 4
        System.out.println(s.search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 3)); // -1
        System.out.println(s.search(new int[] { 1 }, 0));                  // -1
        System.out.println(s.search(new int[] { 3, 1 }, 1));               // 1
    }
}

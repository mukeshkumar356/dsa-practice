/**
 * Given n non-negative integers representing vertical line heights, find
 * two lines that, together with the x-axis, form a container holding the
 * most water. Return the max area.
 *
 * Approach: two pointers starting at both ends of the array. The area
 * between two lines is limited by the SHORTER of the two, so at each step,
 * move the pointer at the shorter line inward - moving the taller one could
 * only ever decrease or keep the width without any chance of increasing
 * height, so it can never produce a better answer. Moving the shorter one
 * is the only move that has a chance of finding a taller line and thus a
 * bigger area.
 *
 * Time complexity:  O(n) - each pointer moves inward at most n times total
 * Space complexity: O(1) - two index variables
 */
public class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int width = right - left;
            int shorterHeight = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, width * shorterHeight);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.maxArea(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 })); // 49
        System.out.println(s.maxArea(new int[] { 1, 1 }));                      // 1
    }
}

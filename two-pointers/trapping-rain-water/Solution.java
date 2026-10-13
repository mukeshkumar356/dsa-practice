/**
 * Trapping Rain Water: given n non-negative integers representing an elevation
 * map where each bar has width 1, compute how much water it can trap after raining.
 *
 * Approach: two pointers from both ends, tracking leftMax and rightMax. Water
 * above a bar is bounded by min(maxLeft, maxRight). Whichever side has the
 * smaller current max is the limiting side, so its water level is already
 * known: advance that pointer and add (max - height) to the total.
 *
 * Time complexity:  O(n) - single pass
 * Space complexity: O(1) - a few variables
 */
public class Solution {

    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, total = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                // Left side is limiting: right wall is at least as tall as height[left].
                if (height[left] >= leftMax) leftMax = height[left];
                else total += leftMax - height[left];
                left++;
            } else {
                if (height[right] >= rightMax) rightMax = height[right];
                else total += rightMax - height[right];
                right--;
            }
        }
        return total;
    }

    private static void check(int[] h, int expected) {
        int got = new Solution().trap(h);
        System.out.println((got == expected ? "PASS " : "FAIL ")
                + java.util.Arrays.toString(h) + " -> " + got);
        if (got != expected) System.exit(1);
    }

    public static void main(String[] args) {
        check(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}, 6);
        check(new int[]{4,2,0,3,2,5}, 9);
        check(new int[]{}, 0);
        check(new int[]{5}, 0);
        check(new int[]{1,2,3,4}, 0);
        check(new int[]{3,0,3}, 3);
    }
}

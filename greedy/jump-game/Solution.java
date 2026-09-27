/**
 * Given an array of non-negative integers where each element represents the
 * maximum jump length from that position, determine if it's possible to
 * reach the last index starting from index 0.
 *
 * Approach: greedy, single pass. Track the furthest index reachable so far
 * ("reach"). While scanning left to right, if the current index is beyond
 * what we can reach, we're stuck - return false immediately. Otherwise,
 * update reach to the furthest this position can jump to. If reach ever
 * covers the last index, we're done.
 *
 * Time complexity:  O(n) - single pass through the array
 * Space complexity: O(1) - just one tracking variable
 */
public class Solution {
    public boolean canJump(int[] nums) {
        int reach = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > reach) {
                return false; // this index is unreachable from anywhere before it
            }
            reach = Math.max(reach, i + nums[i]);

            if (reach >= nums.length - 1) {
                return true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.canJump(new int[] { 2, 3, 1, 1, 4 })); // true
        System.out.println(s.canJump(new int[] { 3, 2, 1, 0, 4 })); // false
        System.out.println(s.canJump(new int[] { 0 }));             // true (already at last index)
    }
}

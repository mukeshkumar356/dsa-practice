/**
 * Given a non-empty array of integers where every element appears exactly
 * twice except for one, find that single element - in linear time and
 * without using extra memory.
 *
 * Approach: XOR every number together. XOR has two properties that make
 * this work:
 *  - a ^ a = 0 (a number XORed with itself cancels out to zero)
 *  - a ^ 0 = a (XOR with zero is a no-op)
 *  - XOR is commutative and associative, so order doesn't matter
 *
 * Every pair cancels itself out to 0, and 0 XORed with the lone number just
 * leaves that number behind.
 *
 * Time complexity:  O(n) - one pass through the array
 * Space complexity: O(1) - a single accumulator variable, no hashmap needed
 */
public class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.singleNumber(new int[] { 2, 2, 1 }));             // 1
        System.out.println(s.singleNumber(new int[] { 4, 1, 2, 1, 2 }));       // 4
        System.out.println(s.singleNumber(new int[] { 1 }));                   // 1
    }
}

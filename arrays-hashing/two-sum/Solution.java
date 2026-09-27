import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer target, return the indices
 * of the two numbers that add up to target.
 *
 * Approach: single pass with a HashMap. For each number, check if its
 * complement (target - number) was already seen. If yes, we found our pair.
 * If no, store the current number's index for future lookups.
 *
 * Time complexity:  O(n) - one pass through the array
 * Space complexity: O(n) - hashmap can hold up to n entries
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (seen.containsKey(complement)) {
                return new int[] { seen.get(complement), i };
            }
            seen.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution exists for the given input");
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] result1 = s.twoSum(new int[] { 2, 7, 11, 15 }, 9);
        System.out.println("Indices: " + result1[0] + ", " + result1[1]); // 0, 1

        int[] result2 = s.twoSum(new int[] { 3, 2, 4 }, 6);
        System.out.println("Indices: " + result2[0] + ", " + result2[1]); // 1, 2
    }
}

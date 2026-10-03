import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer k, return the total number
 * of contiguous subarrays whose sum equals k. The array can contain negative
 * numbers, which rules out a sliding-window approach (the window can't
 * shrink/grow monotonically when sums aren't strictly increasing).
 *
 * Approach: prefix sums + hashmap. Let prefix[i] be the sum of nums[0..i-1].
 * The sum of any subarray nums[j..i-1] is prefix[i] - prefix[j]. We want
 * that to equal k, i.e. prefix[j] = prefix[i] - k. So while scanning left to
 * right and maintaining a running prefix sum, at each index we check how
 * many earlier prefix sums equal (current prefix - k) - each one marks the
 * start of a valid subarray ending here - then record the current prefix
 * sum for future indices to check against.
 *
 * The map starts with {0: 1} to correctly count subarrays that start at
 * index 0 (a prefix sum of exactly k has no "earlier" prefix to subtract,
 * but conceptually subtracts the empty prefix, which is 0).
 *
 * Time complexity:  O(n) - one pass, O(1) average hashmap operations
 * Space complexity: O(n) - prefix sum counts in the worst case
 */
public class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);

        int prefix = 0;
        int count = 0;
        for (int num : nums) {
            prefix += num;
            count += prefixCount.getOrDefault(prefix - k, 0);
            prefixCount.put(prefix, prefixCount.getOrDefault(prefix, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(s.subarraySum(new int[]{1, 1, 1}, 2));        // 2
        System.out.println(s.subarraySum(new int[]{1, 2, 3}, 3));        // 2  ([1,2] and [3])
        System.out.println(s.subarraySum(new int[]{1, -1, 0}, 0));       // 3  ([1,-1], [0], [1,-1,0])
        System.out.println(s.subarraySum(new int[]{3, 4, 7, 2, -3, 1, 4, 2}, 7)); // 4
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of distinct integers, return all possible permutations
 * in any order.
 *
 * Approach: backtracking with a "used" array. Build the permutation one
 * position at a time; at each position try every element that hasn't been
 * used yet, mark it used, recurse, then unmark it (undo the choice). When
 * the current list reaches length n, it is a complete permutation.
 *
 * Time complexity:  O(n * n!) - there are n! permutations, and copying each
 *                    into the result costs O(n)
 * Space complexity: O(n) - recursion depth, current list and used array,
 *                    not counting the output itself
 */
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current)); // complete permutation
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            current.add(nums[i]);
            backtrack(nums, used, current, result);
            current.remove(current.size() - 1); // undo the choice
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.permute(new int[] { 1, 2, 3 }));
        // [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
        System.out.println(s.permute(new int[] { 0, 1 }));
        // [[0, 1], [1, 0]]
        System.out.println(s.permute(new int[] { 1 }));
        // [[1]]
        System.out.println(s.permute(new int[] {}));
        // [[]]  (one empty permutation)
        System.out.println(s.permute(new int[] { 1, 2, 3, 4, 5 }).size() == 120); // true
    }
}

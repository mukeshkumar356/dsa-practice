import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of unique integers, return all possible subsets (the power
 * set), including the empty set and the full array itself.
 *
 * Approach: classic backtracking. At each index, we have two choices -
 * include nums[index] in the current subset, or don't. Recurse through
 * both choices for every element, and record the current subset as a valid
 * result at every step of the recursion (not just at the leaves), since
 * every partial subset built along the way is itself a valid subset.
 *
 * Time complexity:  O(2^n) - there are exactly 2^n subsets of an n-element
 *                    set, and building each one takes O(n), so O(n * 2^n)
 *                    overall including the copies made for the result list
 * Space complexity: O(n) - recursion depth / current subset size,
 *                    not counting the output itself
 */
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current)); // every partial subset is a valid answer

        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1); // undo the choice - this is the "back" in backtracking
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.subsets(new int[] { 1, 2, 3 }));
        // [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
    }
}

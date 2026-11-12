import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, return the length of the longest
 * sequence of consecutive integers (e.g. 1,2,3,4) that can be formed from
 * its elements. Must run in O(n) time.
 *
 * Approach: put every number in a hash set. A number x is the START of a
 * sequence only if x-1 is not in the set. For each such start, walk upward
 * (x+1, x+2, ...) while the numbers exist and track the length. Every number
 * is visited by the inner walk at most once overall, so the total is linear.
 *
 * Time complexity:  O(n) - each element is walked at most once
 * Space complexity: O(n) - the hash set
 */
public class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);

        int best = 0;
        for (int x : set) {
            // Only begin counting at the start of a run
            if (set.contains(x - 1)) continue;
            int cur = x, len = 1;
            while (set.contains(cur + 1)) {
                cur++;
                len++;
            }
            best = Math.max(best, len);
        }
        return best;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] tests = {
            {100, 4, 200, 1, 3, 2},
            {0, 3, 7, 2, 5, 8, 4, 6, 0, 1},
            {},
            {5},
            {1, 2, 0, 1},
            {-3, -2, -1, 10, 11}
        };
        int[] expected = {4, 9, 0, 1, 3, 3};
        boolean ok = true;
        for (int i = 0; i < tests.length; i++) {
            int got = s.longestConsecutive(tests[i]);
            System.out.println("test " + i + ": got " + got + ", expected " + expected[i]
                    + (got == expected[i] ? " OK" : " FAIL"));
            ok &= got == expected[i];
        }
        System.out.println(ok ? "ALL PASSED" : "SOME FAILED");
    }
}

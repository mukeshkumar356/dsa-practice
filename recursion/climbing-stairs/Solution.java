import java.util.HashMap;
import java.util.Map;

/**
 * You're climbing a staircase with n steps. Each time you can climb either
 * 1 or 2 steps. Return how many distinct ways you can reach the top.
 *
 * Approach: this is really just Fibonacci in disguise. To reach step n, the
 * last move was either a single step from (n-1), or a double step from
 * (n-2). So ways(n) = ways(n-1) + ways(n-2), with ways(1) = 1, ways(2) = 2.
 *
 * A naive recursive solution recomputes the same sub-problems exponentially
 * many times, so this uses memoization (top-down DP) to cache each result
 * the first time it's computed.
 *
 * Time complexity:  O(n) - each distinct sub-problem (1..n) is solved once
 * Space complexity: O(n) - the memo map, plus recursion stack depth
 */
public class Solution {
    public int climbStairs(int n) {
        return climb(n, new HashMap<>());
    }

    private int climb(int n, Map<Integer, Integer> memo) {
        if (n <= 2) return n;
        if (memo.containsKey(n)) return memo.get(n);

        int ways = climb(n - 1, memo) + climb(n - 2, memo);
        memo.put(n, ways);
        return ways;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.climbStairs(2)); // 2
        System.out.println(s.climbStairs(3)); // 3
        System.out.println(s.climbStairs(5)); // 8
        System.out.println(s.climbStairs(10)); // 89
    }
}

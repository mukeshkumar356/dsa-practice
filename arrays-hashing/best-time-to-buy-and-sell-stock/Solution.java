/**
 * Best Time to Buy and Sell Stock: given daily prices, choose one day to buy
 * and a later day to sell to maximize profit. Return 0 if no profit is possible.
 *
 * Approach: single pass. Track the lowest price seen so far; at each day the
 * best profit if selling today is price - minSoFar. Keep the maximum of these.
 *
 * Time complexity:  O(n) - one pass
 * Space complexity: O(1)
 */
public class Solution {

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int best = 0;
        for (int p : prices) {
            if (p < minPrice) minPrice = p;       // better day to buy
            else best = Math.max(best, p - minPrice); // sell today
        }
        return best;
    }

    private static void check(String name, int got, int expected) {
        System.out.println((got == expected ? "PASS " : "FAIL ") + name + " -> " + got);
        if (got != expected) throw new AssertionError(name + " expected " + expected);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        check("example", s.maxProfit(new int[]{7, 1, 5, 3, 6, 4}), 5);
        check("decreasing", s.maxProfit(new int[]{7, 6, 4, 3, 1}), 0);
        check("single day", s.maxProfit(new int[]{5}), 0);
        check("increasing", s.maxProfit(new int[]{1, 2, 3, 4, 5}), 4);
        check("min late, early peak", s.maxProfit(new int[]{2, 10, 1, 4}), 8);
    }
}

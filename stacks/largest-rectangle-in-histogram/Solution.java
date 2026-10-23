import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

/**
 * Largest Rectangle in Histogram: given bar heights (each bar has width 1),
 * return the area of the largest rectangle that fits inside the histogram.
 *
 * Approach: monotonic increasing stack of indices. Scan left to right (with a
 * sentinel height 0 at the end to flush the stack). When the current bar is
 * shorter than the bar on top of the stack, that top bar can't extend any
 * further right: pop it, treat it as the rectangle's height, and the width runs
 * from the new stack top + 1 up to i - 1 (or from 0 if the stack is empty).
 * Then push the current index.
 *
 * Time complexity:  O(n) - each index is pushed and popped at most once
 * Space complexity: O(n) - stack in the worst case (strictly increasing input)
 */
public class Solution {

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int best = 0;

        for (int i = 0; i <= n; i++) {
            int cur = (i == n) ? 0 : heights[i];   // sentinel flushes the stack
            while (!stack.isEmpty() && cur < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int left = stack.isEmpty() ? 0 : stack.peek() + 1;
                best = Math.max(best, h * (i - left));
            }
            stack.push(i);
        }
        return best;
    }

    // O(n^2) reference used to cross-check the stack solution.
    private static int brute(int[] h) {
        int best = 0;
        for (int i = 0; i < h.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = i; j < h.length; j++) {
                min = Math.min(min, h[j]);
                best = Math.max(best, min * (j - i + 1));
            }
        }
        return best;
    }

    private static void check(int[] heights, int expected) {
        int got = new Solution().largestRectangleArea(heights);
        System.out.println((got == expected ? "PASS " : "FAIL ")
                + java.util.Arrays.toString(heights) + " -> " + got);
        if (got != expected) {
            throw new AssertionError("expected " + expected);
        }
    }

    public static void main(String[] args) {
        check(new int[]{2, 1, 5, 6, 2, 3}, 10);
        check(new int[]{2, 4}, 4);
        check(new int[]{1, 1, 1, 1}, 4);        // all equal
        check(new int[]{6, 5, 4, 3, 2, 1}, 12); // decreasing
        check(new int[]{1, 2, 3, 4, 5}, 9);     // increasing
        check(new int[]{0, 0, 0}, 0);           // zeros
        check(new int[]{7}, 7);                 // single bar
        check(new int[]{}, 0);                  // empty

        Random rnd = new Random(42);
        for (int t = 0; t < 2000; t++) {
            int[] h = new int[rnd.nextInt(12)];
            for (int i = 0; i < h.length; i++) h[i] = rnd.nextInt(10);
            if (new Solution().largestRectangleArea(h) != brute(h)) {
                throw new AssertionError("mismatch on " + java.util.Arrays.toString(h));
            }
        }
        System.out.println("PASS 2000 random cases match brute force");
    }
}

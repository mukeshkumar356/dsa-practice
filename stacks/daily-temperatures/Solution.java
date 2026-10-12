import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Daily Temperatures: given an array of daily temperatures, return an array
 * where answer[i] is the number of days you must wait after day i to get a
 * warmer temperature (0 if there is no such day).
 *
 * Approach: monotonic decreasing stack of indices. Scan left to right; while
 * the current temperature is higher than the temperature at the index on top
 * of the stack, pop that index and set its answer to (current - popped).
 * Then push the current index. Indices left on the stack never see a warmer day.
 *
 * Time complexity:  O(n) - each index is pushed and popped at most once
 * Space complexity: O(n) - stack in the worst case (non-increasing input)
 */
public class Solution {

    public int[] dailyTemperatures(int[] temps) {
        int n = temps.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temps[i] > temps[stack.peek()]) {
                int prev = stack.pop();
                answer[prev] = i - prev;
            }
            stack.push(i);
        }
        return answer;
    }

    private static void check(int[] temps, int[] expected) {
        int[] got = new Solution().dailyTemperatures(temps);
        boolean ok = Arrays.equals(got, expected);
        System.out.println((ok ? "PASS " : "FAIL ") + Arrays.toString(temps)
                + " -> " + Arrays.toString(got));
        if (!ok) {
            throw new AssertionError("expected " + Arrays.toString(expected));
        }
    }

    public static void main(String[] args) {
        check(new int[]{73, 74, 75, 71, 69, 72, 76, 73}, new int[]{1, 1, 4, 2, 1, 1, 0, 0});
        check(new int[]{30, 40, 50, 60}, new int[]{1, 1, 1, 0});
        check(new int[]{30, 60, 90}, new int[]{1, 1, 0});
        check(new int[]{90, 80, 70}, new int[]{0, 0, 0});   // never warmer
        check(new int[]{50, 50, 50}, new int[]{0, 0, 0});   // equal is not warmer
        check(new int[]{42}, new int[]{0});                 // single element
        check(new int[]{}, new int[]{});                    // empty
    }
}

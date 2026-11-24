import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Min Stack: design a stack supporting push, pop, top and getMin, where every
 * operation runs in constant time.
 *
 * Approach: keep two stacks. The main stack holds all values; the min stack
 * holds the running minimum at each depth (push the new value if it is <= the
 * current minimum). On pop, if the popped value equals the min stack's top,
 * pop the min stack too. The top of the min stack is always the minimum.
 * Using <= keeps duplicates of the minimum correctly tracked.
 *
 * Time complexity:  O(1) for every operation
 * Space complexity: O(n) - up to two stacks of n elements
 */
public class Solution {

    private final Deque<Integer> stack = new ArrayDeque<>();
    private final Deque<Integer> mins = new ArrayDeque<>();

    public void push(int val) {
        stack.push(val);
        if (mins.isEmpty() || val <= mins.peek()) mins.push(val);
    }

    public void pop() {
        int v = stack.pop();
        if (v == mins.peek()) mins.pop(); // int unboxing compare, safe for large values
    }

    public int top() { return stack.peek(); }

    public int getMin() { return mins.peek(); }

    private static void check(String name, int got, int expected) {
        System.out.println((got == expected ? "PASS " : "FAIL ") + name + " -> " + got);
        if (got != expected) throw new AssertionError(name + " expected " + expected);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.push(-2); s.push(0); s.push(-3);
        check("getMin", s.getMin(), -3);
        s.pop();
        check("top", s.top(), 0);
        check("getMin after pop", s.getMin(), -2);

        // duplicate minimums must survive a single pop
        Solution d = new Solution();
        d.push(1); d.push(1); d.push(2);
        d.pop(); d.pop();
        check("duplicate min", d.getMin(), 1);

        // large values (beyond Integer cache range) compare correctly
        Solution b = new Solution();
        b.push(100000); b.push(100000);
        b.pop();
        check("large dup min", b.getMin(), 100000);

        // single element
        Solution one = new Solution();
        one.push(7);
        check("single top", one.top(), 7);
        check("single min", one.getMin(), 7);
    }
}

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Evaluate Reverse Polish Notation: evaluate an arithmetic expression given
 * as an array of tokens in postfix notation. Valid operators are + - * and /;
 * division truncates toward zero.
 *
 * Approach: stack of operands. Scan tokens left to right; push numbers. On an
 * operator, pop the right operand then the left operand, apply the operator,
 * and push the result. The single value left on the stack is the answer.
 *
 * Time complexity:  O(n) - each token is processed once
 * Space complexity: O(n) - stack of operands in the worst case
 */
public class Solution {

    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String t : tokens) {
            switch (t) {
                case "+": stack.push(stack.pop() + stack.pop()); break;
                case "*": stack.push(stack.pop() * stack.pop()); break;
                case "-": { int b = stack.pop(), a = stack.pop(); stack.push(a - b); break; }
                case "/": { int b = stack.pop(), a = stack.pop(); stack.push(a / b); break; } // Java truncates toward zero
                default:  stack.push(Integer.parseInt(t));
            }
        }
        return stack.pop();
    }

    private static void check(String[] tokens, int expected) {
        int got = new Solution().evalRPN(tokens);
        System.out.println((got == expected ? "PASS " : "FAIL ") + String.join(" ", tokens) + " -> " + got);
        if (got != expected) throw new AssertionError("expected " + expected);
    }

    public static void main(String[] args) {
        check(new String[]{"2", "1", "+", "3", "*"}, 9);
        check(new String[]{"4", "13", "5", "/", "+"}, 6);
        check(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}, 22);
        check(new String[]{"7", "-2", "/"}, -3);   // truncation toward zero
        check(new String[]{"3", "4", "-"}, -1);    // operand order matters
        check(new String[]{"42"}, 42);             // single token
    }
}

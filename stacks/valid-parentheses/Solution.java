import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string has valid (properly nested and closed)
 * bracket pairs.
 *
 * Approach: push every opening bracket onto a stack. On a closing bracket,
 * pop the stack and check it matches the expected opening bracket. If the
 * stack is empty at a closing bracket, or a mismatch occurs, or brackets
 * are left on the stack at the end, the string is invalid.
 *
 * Time complexity:  O(n) - one pass through the string
 * Space complexity: O(n) - stack can hold up to n/2 opening brackets
 */
public class Solution {
    private static final Map<Character, Character> PAIRS = Map.of(
        ')', '(',
        ']', '[',
        '}', '{'
    );

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (PAIRS.containsValue(c)) {
                stack.push(c);
            } else if (PAIRS.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != PAIRS.get(c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isValid("()[]{}"));   // true
        System.out.println(s.isValid("(]"));       // false
        System.out.println(s.isValid("([{}])"));   // true
        System.out.println(s.isValid("(("));       // false
    }
}

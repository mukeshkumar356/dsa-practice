/**
 * String to Integer (atoi): Implement myAtoi(String s), which converts a string
 * to a 32-bit signed integer. Skip leading spaces, read an optional '+'/'-'
 * sign, read digits until a non-digit, and clamp the result to the 32-bit
 * signed range [-2^31, 2^31 - 1].
 *
 * Approach: Single left-to-right scan with a small state sequence: skip
 * whitespace, read sign, accumulate digits. To detect overflow without a
 * wider type, check before each step whether result * 10 + digit would exceed
 * Integer.MAX_VALUE (compare against MAX/10 and MAX%10) and clamp immediately.
 *
 * Time complexity:  O(n) - each character is visited at most once
 * Space complexity: O(1) - only a few scalar variables
 */
public class Solution {
    public int myAtoi(String s) {
        int i = 0, n = s.length();
        while (i < n && s.charAt(i) == ' ') i++;          // 1. skip leading spaces

        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) { // 2. optional sign
            if (s.charAt(i) == '-') sign = -1;
            i++;
        }

        int result = 0;
        while (i < n && Character.isDigit(s.charAt(i)) && s.charAt(i) <= '9') { // 3. digits
            int d = s.charAt(i) - '0';
            // Would result*10 + d overflow? Clamp according to sign.
            if (result > Integer.MAX_VALUE / 10
                    || (result == Integer.MAX_VALUE / 10 && d > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + d;
            i++;
        }
        return sign * result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        check(s.myAtoi("42"), 42);
        check(s.myAtoi("   -042"), -42);
        check(s.myAtoi("1337c0d3"), 1337);
        check(s.myAtoi("0-1"), 0);
        check(s.myAtoi("words and 987"), 0);
        check(s.myAtoi(""), 0);                              // empty
        check(s.myAtoi("+"), 0);                             // sign only
        check(s.myAtoi("2147483647"), Integer.MAX_VALUE);    // boundary
        check(s.myAtoi("-2147483648"), Integer.MIN_VALUE);   // boundary
        check(s.myAtoi("91283472332"), Integer.MAX_VALUE);   // overflow clamp
        check(s.myAtoi("-91283472332"), Integer.MIN_VALUE);  // underflow clamp
        System.out.println("All tests passed");
    }

    private static void check(int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError("expected " + expected + " but got " + actual);
        }
    }
}

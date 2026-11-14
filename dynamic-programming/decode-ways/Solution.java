/**
 * Decode Ways: a message of digits is encoded from letters with A=1 ... Z=26.
 * Given a digit string s, return the number of ways to decode it
 * (leading zeros and invalid codes like "06" or "30" are not decodable).
 *
 * Approach: bottom-up DP. Let dp[i] = ways to decode the first i characters.
 *   - If s[i-1] != '0', a single digit works: dp[i] += dp[i-1].
 *   - If s[i-2..i-1] is in [10, 26], a two-digit code works: dp[i] += dp[i-2].
 * Only the last two values are needed, so we roll them into two variables.
 *
 * Time complexity:  O(n)
 * Space complexity: O(1)
 */
public class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0 || s.charAt(0) == '0') return 0;

        int prev2 = 1; // dp[0]: empty prefix has one way
        int prev1 = 1; // dp[1]: first char is non-zero
        for (int i = 2; i <= n; i++) {
            int cur = 0;
            if (s.charAt(i - 1) != '0') cur += prev1;               // single digit 1-9
            int two = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
            if (two >= 10 && two <= 26) cur += prev2;               // two digits 10-26
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] in = {"12", "226", "06", "0", "10", "100", "27", "2101", "1"};
        int[] expected = {2, 3, 0, 0, 1, 0, 1, 1, 1};
        boolean ok = true;
        for (int i = 0; i < in.length; i++) {
            int got = sol.numDecodings(in[i]);
            System.out.println("\"" + in[i] + "\": got " + got + ", expected " + expected[i]
                    + (got == expected[i] ? " OK" : " FAIL"));
            ok &= got == expected[i];
        }
        System.out.println(ok ? "ALL PASSED" : "SOME FAILED");
    }
}

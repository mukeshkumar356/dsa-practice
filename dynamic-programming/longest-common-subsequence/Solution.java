/**
 * Given two strings text1 and text2, return the length of their longest
 * common subsequence (LCS) - a sequence that appears in both strings in the
 * same relative order, but not necessarily contiguously.
 *
 * Approach: bottom-up dynamic programming with a 2D table.
 * dp[i][j] = length of the LCS of text1[0..i) and text2[0..j)
 *
 * - If the characters at text1[i-1] and text2[j-1] match, they extend the
 *   LCS found without either of them by 1: dp[i][j] = dp[i-1][j-1] + 1
 * - Otherwise, we take the best LCS possible by dropping one character from
 *   either string: dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 *
 * Time complexity:  O(m * n) - one entry computed per cell of the table
 * Space complexity: O(m * n) - the DP table itself
 */
public class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.longestCommonSubsequence("abcde", "ace")); // 3 ("ace")
        System.out.println(s.longestCommonSubsequence("abc", "abc"));   // 3
        System.out.println(s.longestCommonSubsequence("abc", "def"));  // 0
    }
}

/**
 * Edit Distance: given two strings word1 and word2, return the minimum number of
 * operations (insert, delete, replace a character) needed to convert word1 to word2.
 *
 * Approach: bottom-up DP. dp[i][j] is the edit distance between the first i chars
 * of word1 and the first j chars of word2. Base cases: dp[i][0] = i (delete all),
 * dp[0][j] = j (insert all). If the characters match, dp[i][j] = dp[i-1][j-1];
 * otherwise 1 + min(dp[i-1][j] delete, dp[i][j-1] insert, dp[i-1][j-1] replace).
 * Only the previous row is needed, so two rolling rows are used.
 *
 * Time complexity:  O(m * n)
 * Space complexity: O(n) with rolling rows
 */
public class Solution {

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[] prev = new int[n + 1];
        int[] cur = new int[n + 1];
        for (int j = 0; j <= n; j++) prev[j] = j;

        for (int i = 1; i <= m; i++) {
            cur[0] = i;
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    cur[j] = prev[j - 1];
                } else {
                    cur[j] = 1 + Math.min(prev[j - 1], Math.min(prev[j], cur[j - 1]));
                }
            }
            int[] tmp = prev; prev = cur; cur = tmp;
        }
        return prev[n];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        check(sol.minDistance("horse", "ros"), 3);
        check(sol.minDistance("intention", "execution"), 5);
        check(sol.minDistance("", "abc"), 3);       // edge: empty source
        check(sol.minDistance("abc", ""), 3);       // edge: empty target
        check(sol.minDistance("", ""), 0);          // edge: both empty
        check(sol.minDistance("same", "same"), 0);
        System.out.println("All tests passed");
    }

    private static void check(int actual, int expected) {
        if (actual != expected) throw new AssertionError("expected " + expected + " got " + actual);
        System.out.println("OK -> " + actual);
    }
}

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Word Break: given a string s and a dictionary of words, return true if s can
 * be segmented into a space-separated sequence of one or more dictionary words
 * (words may be reused).
 *
 * Approach: bottom-up DP. dp[i] is true if the prefix s[0..i) can be segmented.
 * dp[0] = true (empty prefix). For each i, look for a split point j < i where
 * dp[j] is true and s[j..i) is in the dictionary. The answer is dp[n].
 * Only split lengths up to the longest dictionary word need to be checked.
 *
 * Time complexity:  O(n * L * L) - n positions, up to L split points (L = longest
 *                   word), each substring hash costs O(L)
 * Space complexity: O(n + total dictionary size)
 */
public class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int maxLen = 0;
        for (String w : wordDict) maxLen = Math.max(maxLen, w.length());

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 0 && i - j <= maxLen; j--) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        check(sol.wordBreak("leetcode", Arrays.asList("leet", "code")), true);
        check(sol.wordBreak("applepenapple", Arrays.asList("apple", "pen")), true);
        check(sol.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")), false);
        check(sol.wordBreak("aaaaaaa", Arrays.asList("aaaa", "aaa")), true);   // word reuse
        check(sol.wordBreak("a", Arrays.asList("b")), false);                   // edge: no match
        System.out.println("All tests passed");
    }

    private static void check(boolean actual, boolean expected) {
        if (actual != expected) throw new AssertionError("expected " + expected + " got " + actual);
        System.out.println("OK -> " + actual);
    }
}

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 * Approach: expand around center. Every palindrome mirrors around a center that
 * is either a single character (odd length) or the gap between two characters
 * (even length). For each of the 2n-1 centers, expand outward while the
 * characters on both sides match, and remember the widest window found.
 *
 * Time complexity:  O(n^2) - 2n-1 centers, each expanding up to O(n)
 * Space complexity: O(1)   - only indices are tracked; one substring at the end
 */
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) return "";
        int start = 0, end = 0; // inclusive bounds of best palindrome so far
        for (int i = 0; i < s.length(); i++) {
            int odd = expand(s, i, i);       // center on character i
            int even = expand(s, i, i + 1);  // center between i and i+1
            int len = Math.max(odd, even);
            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    // Expands from (l, r) while it stays a palindrome; returns its length.
    private int expand(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.longestPalindrome("babad"));  // bab (or aba)
        System.out.println(sol.longestPalindrome("cbbd"));   // bb
        System.out.println(sol.longestPalindrome("a"));      // a
        System.out.println(sol.longestPalindrome("abcd"));   // a
        System.out.println(sol.longestPalindrome("forgeeksskeegfor")); // geeksskeeg
    }
}

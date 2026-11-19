/**
 * Given an uppercase string s and an integer k, you may change at most k
 * characters to any other uppercase letter. Return the length of the longest
 * substring containing a single repeated letter after such changes.
 *
 * Approach: sliding window with a letter-count array. A window [left, right]
 * is valid if (window length - count of its most frequent letter) <= k, i.e.
 * the minority letters can all be replaced. We track maxFreq, the highest
 * frequency seen in any window so far; it never needs to decrease, because
 * the answer only grows when maxFreq grows. If the window becomes invalid we
 * slide it (move left by one) rather than shrinking, so its size never drops.
 *
 * Time complexity:  O(n) - each index is visited once
 * Space complexity: O(1) - fixed 26-size count array
 */
public class Solution {
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0, maxFreq = 0;
        for (int right = 0; right < s.length(); right++) {
            maxFreq = Math.max(maxFreq, ++count[s.charAt(right) - 'A']);
            // more than k replacements needed: drop the leftmost character
            if (right - left + 1 - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
        }
        // window size never shrinks, so its final size is the best length
        return s.length() - left;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        check(sol.characterReplacement("ABAB", 2), 4);
        check(sol.characterReplacement("AABABBA", 1), 4);
        check(sol.characterReplacement("A", 0), 1);        // single char
        check(sol.characterReplacement("ABCDE", 0), 1);    // no replacements
        check(sol.characterReplacement("AAAA", 2), 4);     // already uniform
        check(sol.characterReplacement("ABCDE", 10), 5);   // k >= length
        System.out.println("All tests passed");
    }

    private static void check(int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError("expected " + expected + " but got " + actual);
        }
    }
}

/**
 * Given strings s and t, return the smallest substring of s that contains every
 * character of t (including duplicates). Return "" if no such window exists.
 *
 * Approach: sliding window with character counts. need[c] holds how many of
 * each character t still requires; `missing` counts the characters still
 * unsatisfied. Expand the right edge, decrementing need; when missing hits 0
 * the window is valid, so shrink from the left as far as possible (stopping
 * when dropping a character would make the window invalid), recording the
 * best window, then drop one required char and continue.
 *
 * Time complexity:  O(|s| + |t|) - each index enters and leaves the window once
 * Space complexity: O(1) - fixed 128-size count array (ASCII)
 */
public class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        int[] need = new int[128];
        for (char c : t.toCharArray()) need[c]++;

        int missing = t.length();
        int bestStart = 0, bestLen = Integer.MAX_VALUE;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            // need[c] > 0 means this char was still required (not surplus)
            if (need[s.charAt(right)]-- > 0) missing--;

            if (missing == 0) {
                // shrink while the left char is surplus (count below zero)
                while (need[s.charAt(left)] < 0) {
                    need[s.charAt(left)]++;
                    left++;
                }
                if (right - left + 1 < bestLen) {
                    bestLen = right - left + 1;
                    bestStart = left;
                }
                // drop the left char, making the window invalid again
                need[s.charAt(left)]++;
                missing++;
                left++;
            }
        }
        return bestLen == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestStart + bestLen);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.minWindow("ADOBECODEBANC", "ABC")); // BANC
        System.out.println(sol.minWindow("a", "a"));               // a
        System.out.println(sol.minWindow("a", "aa"));              // "" (edge: not enough chars)
        System.out.println(sol.minWindow("aab", "aab"));           // aab (duplicates)
        System.out.println(sol.minWindow("abc", "d"));             // "" (char absent)
    }
}

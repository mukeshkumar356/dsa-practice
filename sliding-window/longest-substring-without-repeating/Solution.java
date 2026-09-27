import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, find the length of the longest substring without
 * repeating characters.
 *
 * Approach: sliding window with a hashmap tracking the last seen index of
 * each character. Expand the window by moving `right` forward. If the
 * character at `right` was seen before AND its last position is inside the
 * current window, jump `left` to just past that previous occurrence -
 * this is what keeps the window's contents duplicate-free without needing
 * to shrink it one step at a time.
 *
 * Time complexity:  O(n) - each character is visited once by `right`,
 *                    and `left` only ever moves forward
 * Space complexity: O(min(n, charset size)) - the hashmap holds at most
 *                    one entry per distinct character
 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastSeenAt = new HashMap<>();
        int longest = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (lastSeenAt.containsKey(c) && lastSeenAt.get(c) >= left) {
                left = lastSeenAt.get(c) + 1;
            }

            lastSeenAt.put(c, right);
            longest = Math.max(longest, right - left + 1);
        }

        return longest;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.lengthOfLongestSubstring("abcabcbb")); // 3 ("abc")
        System.out.println(s.lengthOfLongestSubstring("bbbbb"));    // 1 ("b")
        System.out.println(s.lengthOfLongestSubstring("pwwkew"));   // 3 ("wke")
        System.out.println(s.lengthOfLongestSubstring(""));         // 0
    }
}

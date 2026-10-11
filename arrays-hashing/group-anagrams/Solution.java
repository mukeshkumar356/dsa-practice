import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of strings, group the anagrams together. Groups and the
 * strings within them may be returned in any order.
 *
 * Approach: two strings are anagrams iff they have the same character counts.
 * Build a key from the 26 letter counts (e.g. "1#0#0#...") and use it in a
 * HashMap that maps key -> list of words. Counting avoids the extra log factor
 * of sorting each word.
 *
 * Time complexity:  O(n * k) - n words, each of length at most k
 * Space complexity: O(n * k) - map stores every word plus its key
 */
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();

        for (String word : strs) {
            int[] count = new int[26];
            for (char c : word.toCharArray()) {
                count[c - 'a']++;
            }

            // '#' separators keep counts like 1,11 and 11,1 distinct
            StringBuilder key = new StringBuilder();
            for (int n : count) {
                key.append(n).append('#');
            }

            groups.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(word);
        }

        return new ArrayList<>(groups.values());
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        // [[eat, tea, ate], [tan, nat], [bat]] (group order may vary)
        System.out.println(s.groupAnagrams(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" }));

        // Edge case: single empty string -> [[]]
        System.out.println(s.groupAnagrams(new String[] { "" }));

        // Edge case: single letter -> [[a]]
        System.out.println(s.groupAnagrams(new String[] { "a" }));

        // No anagrams at all -> each word alone
        System.out.println(s.groupAnagrams(new String[] { "abc", "def" }));
    }
}

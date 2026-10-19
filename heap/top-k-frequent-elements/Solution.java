import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return the k most frequent
 * elements. The answer may be returned in any order.
 *
 * Approach: bucket sort by frequency. Count occurrences with a hashmap, then
 * place each value into buckets[freq] (a frequency can be at most n). Walk the
 * buckets from the highest frequency down, collecting values until k are
 * gathered. This avoids the O(n log n) sort or O(n log k) heap.
 *
 * Time complexity:  O(n) - one counting pass, one bucket fill, one bucket scan
 * Space complexity: O(n) - the map and the buckets
 */
public class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.merge(num, 1, Integer::sum);
        }

        // buckets[f] holds every value that appears exactly f times
        List<Integer>[] buckets = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> e : count.entrySet()) {
            int f = e.getValue();
            if (buckets[f] == null) {
                buckets[f] = new ArrayList<>();
            }
            buckets[f].add(e.getKey());
        }

        int[] result = new int[k];
        int idx = 0;
        for (int f = buckets.length - 1; f >= 0 && idx < k; f--) {
            if (buckets[f] == null) continue;
            for (int val : buckets[f]) {
                result[idx++] = val;
                if (idx == k) break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] a = s.topKFrequent(new int[] { 1, 1, 1, 2, 2, 3 }, 2);
        Arrays.sort(a);
        System.out.println(Arrays.toString(a)); // [1, 2]
        System.out.println(Arrays.toString(s.topKFrequent(new int[] { 1 }, 1))); // [1]
        int[] b = s.topKFrequent(new int[] { -1, -1, 5, 5, 5, 7 }, 1);
        System.out.println(Arrays.toString(b)); // [5]
        int[] c = s.topKFrequent(new int[] { 4, 4, 4, 3, 3, 2 }, 3);
        Arrays.sort(c);
        System.out.println(Arrays.toString(c)); // [2, 3, 4]
    }
}

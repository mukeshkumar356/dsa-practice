import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Insert Interval: given a sorted list of non-overlapping intervals and a new
 * interval, insert the new interval and merge any overlaps, returning a sorted
 * non-overlapping list.
 *
 * Approach: single linear pass in three phases.
 *  1. Copy all intervals that end before newInterval starts (no overlap).
 *  2. While intervals start at or before newInterval's end, they overlap:
 *     absorb them by widening newInterval to [min(starts), max(ends)].
 *  3. Add the merged newInterval, then copy the remaining intervals.
 *
 * Time complexity:  O(n) - each interval is visited once
 * Space complexity: O(n) - output list (O(1) extra beyond the output)
 */
public class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int i = 0, n = intervals.length;

        // Phase 1: intervals strictly before the new one
        while (i < n && intervals[i][1] < newInterval[0]) res.add(intervals[i++]);

        // Phase 2: merge all overlapping intervals into newInterval
        int start = newInterval[0], end = newInterval[1];
        while (i < n && intervals[i][0] <= end) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        res.add(new int[]{start, end});

        // Phase 3: intervals strictly after
        while (i < n) res.add(intervals[i++]);

        return res.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] in = {
            {{1,3},{6,9}},
            {{1,2},{3,5},{6,7},{8,10},{12,16}},
            {},
            {{1,5}},
            {{1,5}},
            {{3,5},{12,15}}
        };
        int[][] add = {{2,5}, {4,8}, {5,7}, {2,3}, {6,8}, {6,6}};
        String[] exp = {
            "[[1, 5], [6, 9]]",
            "[[1, 2], [3, 10], [12, 16]]",
            "[[5, 7]]",
            "[[1, 5]]",
            "[[1, 5], [6, 8]]",
            "[[3, 5], [6, 6], [12, 15]]"
        };
        boolean ok = true;
        for (int t = 0; t < in.length; t++) {
            String got = Arrays.deepToString(s.insert(in[t], add[t]));
            boolean pass = got.equals(exp[t]);
            ok &= pass;
            System.out.println((pass ? "PASS " : "FAIL ") + got);
        }
        System.out.println(ok ? "All tests passed" : "SOME TESTS FAILED");
    }
}

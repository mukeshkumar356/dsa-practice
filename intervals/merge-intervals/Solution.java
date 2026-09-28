import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [start_i, end_i], merge
 * all overlapping intervals and return the resulting non-overlapping
 * intervals, sorted by start.
 *
 * Approach: sort by start time first. Then walk through the sorted
 * intervals, keeping a "current merged interval" - if the next interval's
 * start is <= the current merged interval's end, they overlap, so extend
 * the end (if needed). Otherwise, the current merged interval is finalized
 * and a new one begins.
 *
 * Time complexity:  O(n log n) - dominated by the sort
 * Space complexity: O(n) - for the sorted copy and the result list
 */
public class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[0][];

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> merged = new ArrayList<>();
        int[] current = intervals[0];
        merged.add(current);

        for (int[] interval : intervals) {
            if (interval[0] <= current[1]) {
                // overlaps with the current merged interval - extend it
                current[1] = Math.max(current[1], interval[1]);
            } else {
                // no overlap - start a new merged interval
                current = interval;
                merged.add(current);
            }
        }

        return merged.toArray(new int[0][]);
    }

    private static void printIntervals(int[][] intervals) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < intervals.length; i++) {
            sb.append(Arrays.toString(intervals[i]));
            if (i < intervals.length - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        printIntervals(s.merge(new int[][] { {1, 3}, {2, 6}, {8, 10}, {15, 18} }));
        // [[1, 6], [8, 10], [15, 18]]
        printIntervals(s.merge(new int[][] { {1, 4}, {4, 5} }));
        // [[1, 5]]
    }
}

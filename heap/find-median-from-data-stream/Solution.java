import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Design a data structure that supports addNum(int num) to add an integer
 * from a data stream and findMedian() to return the median of all elements
 * added so far (the mean of the two middle values when the count is even).
 *
 * Approach: two heaps. A max-heap (lo) holds the smaller half and a min-heap
 * (hi) holds the larger half. Invariant: every element of lo <= every element
 * of hi, and lo.size() == hi.size() or lo.size() == hi.size() + 1. On add,
 * push into lo, move lo's max into hi, then rebalance so lo is never smaller.
 * The median is lo's top (odd count) or the mean of both tops (even count).
 *
 * Time complexity:  addNum O(log n), findMedian O(1)
 * Space complexity: O(n) - all elements are stored across the two heaps
 */
public class Solution {
    private final PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Integer> hi = new PriorityQueue<>();

    public void addNum(int num) {
        lo.offer(num);
        hi.offer(lo.poll()); // guarantees max(lo) <= min(hi)
        if (hi.size() > lo.size()) {
            lo.offer(hi.poll());
        }
    }

    public double findMedian() {
        if (lo.size() > hi.size()) {
            return lo.peek();
        }
        // use long arithmetic to avoid int overflow when summing the two middles
        return ((long) lo.peek() + hi.peek()) / 2.0;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.addNum(1);
        System.out.println(s.findMedian()); // 1.0
        s.addNum(2);
        System.out.println(s.findMedian()); // 1.5
        s.addNum(3);
        System.out.println(s.findMedian()); // 2.0

        Solution t = new Solution(); // negatives, duplicates, overflow edge
        int[] vals = { -5, -5, 7, Integer.MAX_VALUE, Integer.MAX_VALUE };
        double[] expected = { -5.0, -5.0, -5.0, 1.0, 7.0 };
        for (int i = 0; i < vals.length; i++) {
            t.addNum(vals[i]);
            double m = t.findMedian();
            System.out.println(m + (m == expected[i] ? " ok" : " FAIL expected " + expected[i]));
        }
    }
}

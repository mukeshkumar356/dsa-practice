import java.util.PriorityQueue;

/**
 * Given an integer array nums and an integer k, return the kth largest
 * element in the array (the kth largest in sorted order, not the kth
 * distinct value).
 *
 * Approach: min-heap of size k. Push numbers onto the heap; whenever it
 * grows past size k, pop the smallest. After processing every number, the
 * heap contains exactly the k largest values seen, and the smallest of
 * those (the heap's root) is the kth largest overall.
 *
 * Time complexity:  O(n log k) - each of the n insertions/removals on a
 *                    heap of size k costs O(log k)
 * Space complexity: O(k) - the heap never holds more than k elements
 */
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.findKthLargest(new int[] { 3, 2, 1, 5, 6, 4 }, 2));       // 5
        System.out.println(s.findKthLargest(new int[] { 3, 2, 3, 1, 2, 4, 5, 5, 6 }, 4)); // 4
    }
}

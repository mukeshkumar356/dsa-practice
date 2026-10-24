import java.util.PriorityQueue;

/**
 * Merge k Sorted Lists: given an array of k singly linked lists, each sorted in
 * ascending order, merge them into one sorted linked list and return it.
 *
 * Approach: keep a min-heap holding the current head of every non-empty list.
 * Repeatedly pop the smallest node, append it to the result via a dummy head,
 * and push that node's successor (if any). The heap never holds more than k nodes.
 *
 * Time complexity:  O(N log k), N = total number of nodes
 * Space complexity: O(k) for the heap (nodes are relinked, not copied)
 */
public class Solution {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        for (ListNode head : lists) {
            if (head != null) heap.offer(head);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            ListNode smallest = heap.poll();
            tail.next = smallest;
            tail = smallest;
            if (smallest.next != null) heap.offer(smallest.next);
        }
        return dummy.next;
    }

    // ---- helpers for the quick verification below ----
    static ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    static String toStr(ListNode n) {
        StringBuilder sb = new StringBuilder("[");
        for (; n != null; n = n.next) sb.append(n.val).append(n.next != null ? "," : "");
        return sb.append("]").toString();
    }

    static void check(String actual, String expected) {
        if (!actual.equals(expected)) throw new AssertionError("expected " + expected + " but got " + actual);
        System.out.println("PASS " + actual);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        check(toStr(sol.mergeKLists(new ListNode[]{build(1, 4, 5), build(1, 3, 4), build(2, 6)})),
              "[1,1,2,3,4,4,5,6]");
        check(toStr(sol.mergeKLists(new ListNode[]{})), "[]");                    // edge: no lists
        check(toStr(sol.mergeKLists(new ListNode[]{null, build(2), null})), "[2]"); // edge: empty lists mixed in
        check(toStr(sol.mergeKLists(new ListNode[]{build(-3, -1), build(-2, 0, 0)})),
              "[-3,-2,-1,0,0]");                                                  // negatives, duplicates
    }
}

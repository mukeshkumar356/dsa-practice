/**
 * Merge Two Sorted Lists: given the heads of two sorted singly linked lists,
 * merge them into one sorted list by splicing together the existing nodes.
 *
 * Approach: use a dummy head and a tail pointer. Repeatedly attach the
 * smaller of the two current nodes to the tail and advance that list.
 * When one list runs out, attach the remainder of the other. Using <= when
 * comparing keeps the merge stable.
 *
 * Time complexity:  O(m + n) - each node is visited once
 * Space complexity: O(1)     - nodes are relinked, no new nodes allocated
 */
public class Solution {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = (a != null) ? a : b; // attach leftover tail
        return dummy.next;
    }

    // ---- helpers for testing ----
    static ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), t = dummy;
        for (int v : vals) {
            t.next = new ListNode(v);
            t = t.next;
        }
        return dummy.next;
    }

    static String str(ListNode n) {
        StringBuilder sb = new StringBuilder("[");
        for (; n != null; n = n.next) {
            sb.append(n.val);
            if (n.next != null) sb.append(",");
        }
        return sb.append("]").toString();
    }

    static void check(String actual, String expected) {
        System.out.println(actual + (actual.equals(expected) ? "  OK" : "  FAIL expected " + expected));
        if (!actual.equals(expected)) System.exit(1);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        check(str(s.mergeTwoLists(build(1, 2, 4), build(1, 3, 4))), "[1,1,2,3,4,4]");
        check(str(s.mergeTwoLists(null, null)), "[]");
        check(str(s.mergeTwoLists(null, build(0))), "[0]");
        check(str(s.mergeTwoLists(build(5, 6, 7), build(1, 2))), "[1,2,5,6,7]");
    }
}

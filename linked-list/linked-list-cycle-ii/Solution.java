/**
 * Linked List Cycle II: given the head of a linked list, return the node where
 * the cycle begins, or null if the list has no cycle.
 *
 * Approach: Floyd's tortoise and hare. Move slow by 1 and fast by 2. If they
 * meet, there is a cycle. Then reset one pointer to head and advance both by 1
 * step at a time; they meet exactly at the cycle's entry node.
 * (Proof: if the head-to-entry distance is a and the meeting point is b steps
 * into the cycle of length L, then 2(a+b) = a + b + kL, so a = kL - b, i.e.
 * walking a steps from the meeting point lands on the entry.)
 *
 * Time complexity:  O(n) - each pointer travels a linear number of steps
 * Space complexity: O(1) - only two pointers
 */
public class Solution {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    // Builds a list from values; if pos >= 0, tail links back to node at index pos.
    private static ListNode build(int pos, int... values) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        ListNode entry = null;
        for (int i = 0; i < values.length; i++) {
            tail.next = new ListNode(values[i]);
            tail = tail.next;
            if (i == pos) entry = tail;
        }
        if (pos >= 0) tail.next = entry;
        return dummy.next;
    }

    private static void check(String name, ListNode head, Integer expectedVal) {
        ListNode res = new Solution().detectCycle(head);
        Integer got = res == null ? null : res.val;
        boolean ok = (got == null) ? expectedVal == null : got.equals(expectedVal);
        System.out.println(name + ": " + got + (ok ? " PASS" : " FAIL (expected " + expectedVal + ")"));
    }

    public static void main(String[] args) {
        check("cycle at index 1", build(1, 3, 2, 0, -4), 2);
        check("cycle at head (2 nodes)", build(0, 1, 2), 1);
        check("single node self-loop", build(0, 1), 1);
        check("single node no cycle", build(-1, 1), null);
        check("no cycle", build(-1, 1, 2, 3, 4), null);
        check("empty list", null, null);
    }
}

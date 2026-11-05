/**
 * Reorder List: given a singly linked list L0 -> L1 -> ... -> Ln-1 -> Ln,
 * reorder it in place to L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...
 *
 * Approach: three steps.
 *   1. Find the middle with slow/fast pointers.
 *   2. Reverse the second half.
 *   3. Merge the two halves by alternating nodes.
 *
 * Time complexity:  O(n) - three linear passes
 * Space complexity: O(1) - in-place pointer manipulation
 */
public class Solution {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // 1. Find the middle: slow ends at the last node of the first half.
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Reverse the second half and cut the list in two.
        ListNode prev = null, cur = slow.next;
        slow.next = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        // 3. Interleave: first half is >= second half in length.
        ListNode a = head, b = prev;
        while (b != null) {
            ListNode aNext = a.next, bNext = b.next;
            a.next = b;
            b.next = aNext;
            a = aNext;
            b = bNext;
        }
    }

    static ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), t = dummy;
        for (int v : vals) {
            t.next = new ListNode(v);
            t = t.next;
        }
        return dummy.next;
    }

    static String str(ListNode n) {
        StringBuilder sb = new StringBuilder();
        for (; n != null; n = n.next) {
            sb.append(n.val).append(n.next != null ? "," : "");
        }
        return sb.toString();
    }

    static boolean check(int[] in, String expected) {
        ListNode h = build(in);
        new Solution().reorderList(h);
        String got = str(h);
        System.out.println((got.equals(expected) ? "PASS " : "FAIL ") + got);
        return got.equals(expected);
    }

    public static void main(String[] args) {
        boolean ok = check(new int[]{1, 2, 3, 4}, "1,4,2,3");
        ok &= check(new int[]{1, 2, 3, 4, 5}, "1,5,2,4,3");
        ok &= check(new int[]{1}, "1");
        ok &= check(new int[]{1, 2}, "1,2");
        ok &= check(new int[]{}, "");
        System.out.println(ok ? "ALL PASS" : "SOME FAILED");
    }
}

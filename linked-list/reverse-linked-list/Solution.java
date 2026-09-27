/**
 * Reverse a singly linked list, iteratively.
 *
 * Approach: walk the list once, keeping track of the previous node. At each
 * step, save the next node before overwriting current.next to point
 * backwards to prev. Then advance prev and current forward.
 *
 * Time complexity:  O(n) - visits every node once
 * Space complexity: O(1) - only a few pointers, no extra data structure
 */
public class Solution {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }

        return prev;
    }

    private static ListNode buildList(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : values) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    private static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = buildList(1, 2, 3, 4, 5);
        printList(head);                     // 1 -> 2 -> 3 -> 4 -> 5
        ListNode reversed = s.reverseList(head);
        printList(reversed);                 // 5 -> 4 -> 3 -> 2 -> 1
    }
}

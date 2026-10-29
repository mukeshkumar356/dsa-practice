import java.util.HashMap;
import java.util.Map;

/**
 * Design a Least Recently Used (LRU) cache with a fixed capacity. get(key)
 * returns the value or -1 if absent; put(key, value) inserts or updates the
 * key and, if the capacity is exceeded, evicts the least recently used key.
 * Both operations must run in O(1) average time.
 *
 * Approach: a hash map from key to node, plus a doubly linked list ordered
 * by recency (head side = most recent, tail side = least recent). The map
 * gives O(1) lookup; the list lets us unlink a node and move it to the front
 * in O(1). Sentinel head/tail nodes remove all null/edge-case checks.
 * Every get/put moves the touched node to the front; on overflow we remove
 * the node just before the tail sentinel.
 *
 * Time complexity:  O(1) per get / put
 * Space complexity: O(capacity) - map entries and list nodes
 */
public class Solution {
    private static class Node {
        int key, val;
        Node prev, next;
        Node(int key, int val) { this.key = key; this.val = val; }
    }

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(0, 0); // sentinel: most recent side
    private final Node tail = new Node(0, 0); // sentinel: least recent side

    public Solution(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node n = map.get(key);
        if (n == null) return -1;
        remove(n);
        addFront(n); // mark as most recently used
        return n.val;
    }

    public void put(int key, int value) {
        Node n = map.get(key);
        if (n != null) {
            n.val = value;
            remove(n);
            addFront(n);
            return;
        }
        if (map.size() == capacity) {
            Node lru = tail.prev; // least recently used
            remove(lru);
            map.remove(lru.key);
        }
        n = new Node(key, value);
        map.put(key, n);
        addFront(n);
    }

    private void remove(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    private void addFront(Node n) {
        n.next = head.next;
        n.prev = head;
        head.next.prev = n;
        head.next = n;
    }

    private static void check(int actual, int expected) {
        System.out.println(actual + (actual == expected ? " OK" : " FAIL (expected " + expected + ")"));
    }

    public static void main(String[] args) {
        // Classic example, capacity 2
        Solution c = new Solution(2);
        c.put(1, 1);
        c.put(2, 2);
        check(c.get(1), 1);   // 1 becomes most recent
        c.put(3, 3);          // evicts key 2
        check(c.get(2), -1);
        c.put(4, 4);          // evicts key 1
        check(c.get(1), -1);
        check(c.get(3), 3);
        check(c.get(4), 4);

        // Update existing key refreshes recency and value
        Solution u = new Solution(2);
        u.put(1, 10);
        u.put(2, 20);
        u.put(1, 11);         // key 2 is now LRU
        u.put(3, 30);         // evicts key 2
        check(u.get(2), -1);
        check(u.get(1), 11);

        // Edge case: capacity 1
        Solution one = new Solution(1);
        one.put(1, 1);
        one.put(2, 2);
        check(one.get(1), -1);
        check(one.get(2), 2);
    }
}

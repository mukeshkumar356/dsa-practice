import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Clone Graph: given a reference to a node in a connected undirected graph,
 * return a deep copy (clone) of the graph. Each node has a value and a list
 * of neighbors.
 *
 * Approach: BFS with a hash map from original node -> cloned node. The map
 * doubles as the "visited" set. When we first see a node we create its clone
 * and enqueue it; for every neighbor we make sure a clone exists, then link
 * the clone of the current node to the clone of the neighbor.
 *
 * Time complexity:  O(V + E) - every node and edge is processed once
 * Space complexity: O(V) - the map plus the queue
 */
public class Solution {

    static class Node {
        int val;
        List<Node> neighbors = new ArrayList<>();
        Node(int val) { this.val = val; }
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Map<Node, Node> copies = new HashMap<>();
        copies.put(node, new Node(node.val));
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node nb : cur.neighbors) {
                if (!copies.containsKey(nb)) {
                    copies.put(nb, new Node(nb.val));
                    queue.add(nb);
                }
                copies.get(cur).neighbors.add(copies.get(nb));
            }
        }
        return copies.get(node);
    }

    // Build a graph from an adjacency list (1-indexed node values).
    static Node build(int[][] adj) {
        if (adj.length == 0) return null;
        Node[] nodes = new Node[adj.length + 1];
        for (int i = 1; i <= adj.length; i++) nodes[i] = new Node(i);
        for (int i = 1; i <= adj.length; i++)
            for (int j : adj[i - 1]) nodes[i].neighbors.add(nodes[j]);
        return nodes[1];
    }

    // Serialize back to an adjacency list via BFS, ordered by node value.
    static String serialize(Node start) {
        if (start == null) return "[]";
        Map<Integer, Node> seen = new HashMap<>();
        Queue<Node> q = new ArrayDeque<>();
        seen.put(start.val, start);
        q.add(start);
        while (!q.isEmpty()) {
            Node c = q.poll();
            for (Node nb : c.neighbors)
                if (seen.putIfAbsent(nb.val, nb) == null) q.add(nb);
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 1; i <= seen.size(); i++) {
            sb.append("[");
            List<Node> nbs = seen.get(i).neighbors;
            for (int j = 0; j < nbs.size(); j++) {
                if (j > 0) sb.append(",");
                sb.append(nbs.get(j).val);
            }
            sb.append("]");
            if (i < seen.size()) sb.append(",");
        }
        return sb.append("]").toString();
    }

    // True if no node of the clone is shared with the original graph.
    static boolean disjoint(Node a, Node b) {
        java.util.Set<Node> orig = new java.util.HashSet<>();
        Queue<Node> q = new ArrayDeque<>();
        orig.add(a); q.add(a);
        while (!q.isEmpty()) {
            for (Node nb : q.poll().neighbors) if (orig.add(nb)) q.add(nb);
        }
        java.util.Set<Node> seen = new java.util.HashSet<>();
        q.add(b); seen.add(b);
        while (!q.isEmpty()) {
            Node c = q.poll();
            if (orig.contains(c)) return false;
            for (Node nb : c.neighbors) if (seen.add(nb)) q.add(nb);
        }
        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] tests = {
            {{2, 4}, {1, 3}, {2, 4}, {1, 3}},  // 4-cycle
            {{}},                               // single node, no edges
            {{2}, {1}},                         // two nodes
            {{2, 3}, {1, 3}, {1, 2}},           // triangle
        };
        String[] expected = {"[[2,4],[1,3],[2,4],[1,3]]", "[[]]", "[[2],[1]]", "[[2,3],[1,3],[1,2]]"};
        for (int i = 0; i < tests.length; i++) {
            Node orig = build(tests[i]);
            Node copy = s.cloneGraph(orig);
            String out = serialize(copy);
            boolean ok = out.equals(expected[i]) && copy != orig && disjoint(orig, copy);
            System.out.println(out + " -> " + (ok ? "PASS" : "FAIL"));
        }
        System.out.println(s.cloneGraph(null) == null ? "null -> PASS" : "null -> FAIL");
    }
}

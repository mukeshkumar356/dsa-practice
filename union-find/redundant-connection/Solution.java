import java.util.Arrays;

/**
 * Redundant Connection: A tree with n nodes (labeled 1..n) had one extra
 * edge added, producing a graph given as edges[i] = [a, b]. Return the edge
 * that can be removed so the graph becomes a tree again; if several answers
 * exist, return the one that occurs last in the input.
 *
 * Approach: Union-Find. Process edges in order. If both endpoints already
 * share a root, adding this edge closes a cycle, so it is redundant. Since
 * only one extra edge exists and we scan in order, the first edge that
 * closes a cycle is the last-occurring valid answer. Path compression plus
 * union by rank keeps operations near O(1).
 *
 * Time complexity:  O(n * alpha(n)) - one find/union pair per edge
 * Space complexity: O(n) - parent and rank arrays
 */
public class Solution {
    private int[] parent;
    private int[] rank_;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1];
        rank_ = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        for (int[] e : edges) {
            if (!union(e[0], e[1])) {
                return e;
            }
        }
        return new int[0];
    }

    private int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]]; // path halving
            x = parent[x];
        }
        return x;
    }

    // Returns false if a and b were already connected.
    private boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return false;
        if (rank_[ra] < rank_[rb]) {
            parent[ra] = rb;
        } else if (rank_[ra] > rank_[rb]) {
            parent[rb] = ra;
        } else {
            parent[rb] = ra;
            rank_[ra]++;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] tests = {
            {{1, 2}, {1, 3}, {2, 3}},
            {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}},
            {{1, 2}, {1, 2}}, // edge case: duplicate edge
        };
        int[][] expected = {{2, 3}, {1, 4}, {1, 2}};
        for (int i = 0; i < tests.length; i++) {
            int[] r = new Solution().findRedundantConnection(tests[i]);
            System.out.println(Arrays.toString(r) + " expected " + Arrays.toString(expected[i])
                + (Arrays.equals(r, expected[i]) ? " OK" : " FAIL"));
        }
    }
}

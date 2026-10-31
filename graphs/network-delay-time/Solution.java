import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Network Delay Time: a network has n nodes labeled 1..n and directed, weighted
 * edges times[i] = {u, v, w} (a signal takes w time to go from u to v). A signal
 * is sent from node k. Return the time for all nodes to receive it, or -1 if
 * some node is unreachable.
 *
 * Approach: Dijkstra's shortest paths from k using a min-heap of (distance, node).
 * Pop the closest unsettled node, skip stale heap entries, and relax its outgoing
 * edges. The answer is the largest shortest distance; if any node stayed at
 * infinity it is unreachable, so return -1.
 *
 * Time complexity:  O(E log V) - each edge relaxation may push onto the heap
 * Space complexity: O(V + E)   - adjacency list, distance array and heap
 */
public class Solution {

    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int[] t : times) graph.get(t[0]).add(new int[]{t[1], t[2]});

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        heap.add(new int[]{0, k});

        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int d = cur[0], u = cur[1];
            if (d > dist[u]) continue; // stale entry
            for (int[] edge : graph.get(u)) {
                int v = edge[0], nd = d + edge[1];
                if (nd < dist[v]) {
                    dist[v] = nd;
                    heap.add(new int[]{nd, v});
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            max = Math.max(max, dist[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2) + " (expected 2)");
        System.out.println(s.networkDelayTime(new int[][]{{1, 2, 1}}, 2, 1) + " (expected 1)");
        System.out.println(s.networkDelayTime(new int[][]{{1, 2, 1}}, 2, 2) + " (expected -1)");
        // Edge: single node
        System.out.println(s.networkDelayTime(new int[][]{}, 1, 1) + " (expected 0)");
        // A shorter indirect path beats the direct edge
        System.out.println(s.networkDelayTime(new int[][]{{1, 2, 10}, {1, 3, 1}, {3, 2, 1}}, 3, 1) + " (expected 2)");
    }
}

/**
 * There are n cities. Some of them are connected, while some are not. If
 * city a is connected directly with city b, and b is connected directly
 * with city c, then a is connected indirectly with c. A province is a
 * group of directly or indirectly connected cities and no other cities
 * outside of the group.
 *
 * Given an n x n adjacency matrix isConnected where isConnected[i][j] = 1
 * if the ith city and the jth city are directly connected, and
 * isConnected[i][j] = 0 otherwise, return the total number of provinces.
 *
 * Approach: Union-Find (Disjoint Set Union). Start with every city in its
 * own set. For every pair (i, j) marked connected, union their sets. The
 * final number of provinces is simply the number of distinct roots left
 * standing once every connection has been processed - path compression
 * and union by rank keep each find/union call nearly O(1).
 *
 * Time complexity:  O(n^2 * alpha(n)) - scanning the matrix dominates;
 *                    alpha(n) (inverse Ackermann) is effectively constant
 * Space complexity: O(n) - parent and rank arrays
 */
public class Solution {
    private int[] parent;
    private int[] rank_;

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        parent = new int[n];
        rank_ = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        int provinces = 0;
        for (int i = 0; i < n; i++) {
            if (find(i) == i) {
                provinces++;
            }
        }
        return provinces;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }

    private void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return;

        if (rank_[rootA] < rank_[rootB]) {
            parent[rootA] = rootB;
        } else if (rank_[rootA] > rank_[rootB]) {
            parent[rootB] = rootA;
        } else {
            parent[rootB] = rootA;
            rank_[rootA]++;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        int[][] grid1 = {
            {1, 1, 0},
            {1, 1, 0},
            {0, 0, 1}
        };
        System.out.println(s.findCircleNum(grid1)); // 2

        int[][] grid2 = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        System.out.println(s.findCircleNum(grid2)); // 3
    }
}

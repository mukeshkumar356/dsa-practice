import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Rotting Oranges: a grid has 0 = empty, 1 = fresh orange, 2 = rotten orange.
 * Every minute, fresh oranges 4-directionally adjacent to a rotten one become
 * rotten. Return the minimum minutes until no fresh orange remains, or -1 if
 * that is impossible.
 *
 * Approach: multi-source BFS. Seed the queue with all initially rotten oranges
 * and count the fresh ones. Process the queue level by level; each level is one
 * minute. When a fresh neighbor is infected, mark it rotten, decrement the fresh
 * count and enqueue it. If fresh oranges remain after BFS, return -1.
 *
 * Time complexity:  O(rows * cols) - each cell is enqueued at most once
 * Space complexity: O(rows * cols) - queue in the worst case
 */
public class Solution {

    public int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        int fresh = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) queue.add(new int[]{r, c});
                else if (grid[r][c] == 1) fresh++;
            }
        }

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int minutes = 0;

        while (!queue.isEmpty() && fresh > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] d : dirs) {
                    int nr = cur[0] + d[0], nc = cur[1] + d[1];
                    if (nr < 0 || nc < 0 || nr >= rows || nc >= cols || grid[nr][nc] != 1) continue;
                    grid[nr][nc] = 2;
                    fresh--;
                    queue.add(new int[]{nr, nc});
                }
            }
            minutes++;
        }
        return fresh == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}) + " (expected 4)");
        System.out.println(s.orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}) + " (expected -1)");
        // Edge: no fresh oranges at all
        System.out.println(s.orangesRotting(new int[][]{{0, 2}}) + " (expected 0)");
        System.out.println(s.orangesRotting(new int[][]{{0}}) + " (expected 0)");
        // Edge: fresh but no rotten source
        System.out.println(s.orangesRotting(new int[][]{{1}}) + " (expected -1)");
    }
}

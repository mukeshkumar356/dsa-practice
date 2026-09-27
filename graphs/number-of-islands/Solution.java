/**
 * Given an m x n 2D binary grid which represents a map of '1's (land) and
 * '0's (water), return the number of islands. An island is surrounded by
 * water and is formed by connecting adjacent lands horizontally or
 * vertically.
 *
 * Approach: scan every cell. On finding an unvisited '1', that's a brand
 * new island - increment the count and flood-fill (DFS) in all 4
 * directions, marking every connected land cell as visited (by sinking it
 * to '0') so it's never counted again.
 *
 * Time complexity:  O(rows * cols) - every cell is visited at most twice
 *                    (once by the outer scan, once by the flood fill)
 * Space complexity: O(rows * cols) - worst case recursion depth if the
 *                    entire grid is one connected island
 */
public class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int islands = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    islands++;
                    sink(grid, r, c);
                }
            }
        }

        return islands;
    }

    private void sink(char[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != '1') {
            return;
        }

        grid[r][c] = '0'; // mark visited

        sink(grid, r + 1, c);
        sink(grid, r - 1, c);
        sink(grid, r, c + 1);
        sink(grid, r, c - 1);
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        char[][] grid1 = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        System.out.println(s.numIslands(grid1)); // 1

        char[][] grid2 = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        System.out.println(s.numIslands(grid2)); // 3
    }
}

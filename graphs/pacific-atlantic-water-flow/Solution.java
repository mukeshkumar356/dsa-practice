import java.util.ArrayList;
import java.util.List;

/**
 * Pacific Atlantic Water Flow: given an m x n grid of heights, water can flow
 * from a cell to a 4-directional neighbor with height <= its own. The Pacific
 * touches the top and left edges, the Atlantic the bottom and right edges.
 * Return every cell from which water can reach BOTH oceans.
 *
 * Approach: reverse the flow. Run a DFS inward from each ocean's border cells,
 * moving only to neighbors with height >= current (uphill), marking every cell
 * that can reach that ocean. The answer is the intersection of the two marked
 * sets.
 *
 * Time complexity:  O(m * n) - each cell is visited at most once per ocean
 * Space complexity: O(m * n) - two visited grids plus recursion stack
 */
public class Solution {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) return result;
        int m = heights.length, n = heights[0].length;
        boolean[][] pac = new boolean[m][n];
        boolean[][] atl = new boolean[m][n];

        for (int r = 0; r < m; r++) {
            dfs(heights, pac, r, 0);
            dfs(heights, atl, r, n - 1);
        }
        for (int c = 0; c < n; c++) {
            dfs(heights, pac, 0, c);
            dfs(heights, atl, m - 1, c);
        }

        for (int r = 0; r < m; r++)
            for (int c = 0; c < n; c++)
                if (pac[r][c] && atl[r][c]) result.add(List.of(r, c));
        return result;
    }

    private void dfs(int[][] h, boolean[][] seen, int r, int c) {
        seen[r][c] = true;
        for (int[] d : DIRS) {
            int nr = r + d[0], nc = c + d[1];
            if (nr < 0 || nc < 0 || nr >= h.length || nc >= h[0].length) continue;
            if (seen[nr][nc] || h[nr][nc] < h[r][c]) continue;
            dfs(h, seen, nr, nc);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] a = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(s.pacificAtlantic(a));
        // expected [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
        System.out.println(s.pacificAtlantic(new int[][]{{1}}));   // [[0, 0]]
        System.out.println(s.pacificAtlantic(new int[][]{{2,1},{1,2}})); // [[0,0],[0,1],[1,0],[1,1]]
        System.out.println(s.pacificAtlantic(new int[][]{{3,3,3}})); // all three cells
    }
}

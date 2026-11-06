/**
 * Given an m x n grid of characters and a string word, return true if word
 * exists in the grid. The word is built from sequentially adjacent
 * (horizontal/vertical) cells, and a cell may not be used twice in one path.
 *
 * Approach: backtracking DFS. Start from every cell matching word[0]. At each
 * step mark the current cell as visited (temporarily overwrite it with '#'),
 * try the four neighbours for the next character, then restore the cell
 * (undo the choice). Stop as soon as the whole word is matched.
 *
 * Time complexity:  O(m * n * 3^L) - L = word length; after the first step
 *                    each move has at most 3 unvisited neighbours
 * Space complexity: O(L) - recursion depth (visited marks are done in place)
 */
public class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int r = 0; r < m; r++)
            for (int c = 0; c < n; c++)
                if (dfs(board, word, 0, r, c)) return true;
        return false;
    }

    private boolean dfs(char[][] b, String w, int idx, int r, int c) {
        if (idx == w.length()) return true; // matched every character
        if (r < 0 || c < 0 || r >= b.length || c >= b[0].length || b[r][c] != w.charAt(idx))
            return false;
        char saved = b[r][c];
        b[r][c] = '#'; // mark visited
        boolean found = dfs(b, w, idx + 1, r + 1, c) || dfs(b, w, idx + 1, r - 1, c)
                || dfs(b, w, idx + 1, r, c + 1) || dfs(b, w, idx + 1, r, c - 1);
        b[r][c] = saved; // undo the choice
        return found;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        char[][] g = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(s.exist(g, "ABCCED")); // true
        System.out.println(s.exist(g, "SEE"));    // true
        System.out.println(s.exist(g, "ABCB"));   // false (cell reuse)
        System.out.println(s.exist(new char[][]{{'a'}}, "a")); // true (edge: 1x1)
        System.out.println(s.exist(new char[][]{{'a'}}, "ab")); // false
    }
}

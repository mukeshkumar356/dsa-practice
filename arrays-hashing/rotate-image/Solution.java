import java.util.Arrays;

/**
 * You are given an n x n 2D matrix representing an image. Rotate the image
 * by 90 degrees clockwise, in-place (do not allocate another matrix).
 *
 * Approach: rotating clockwise equals transposing the matrix (swap
 * m[i][j] with m[j][i]) and then reversing every row.
 *   [1 2 3]      [1 4 7]      [7 4 1]
 *   [4 5 6]  ->  [2 5 8]  ->  [8 5 2]
 *   [7 8 9]      [3 6 9]      [9 6 3]
 *
 * Time complexity:  O(n^2) - every cell is touched a constant number of times
 * Space complexity: O(1)   - in-place swaps only
 */
public class Solution {
    public void rotate(int[][] m) {
        int n = m.length;
        // Step 1: transpose (only the upper triangle, to swap each pair once)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int t = m[i][j]; m[i][j] = m[j][i]; m[j][i] = t;
            }
        }
        // Step 2: reverse each row
        for (int[] row : m) {
            for (int l = 0, r = n - 1; l < r; l++, r--) {
                int t = row[l]; row[l] = row[r]; row[r] = t;
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] tests = {
            {{1,2,3},{4,5,6},{7,8,9}},
            {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}},
            {{1}},
            {{1,2},{3,4}},
            {}
        };
        int[][][] expected = {
            {{7,4,1},{8,5,2},{9,6,3}},
            {{15,13,2,5},{14,3,4,1},{12,6,8,9},{16,7,10,11}},
            {{1}},
            {{3,1},{4,2}},
            {}
        };
        boolean ok = true;
        for (int t = 0; t < tests.length; t++) {
            s.rotate(tests[t]);
            boolean pass = Arrays.deepEquals(tests[t], expected[t]);
            ok &= pass;
            System.out.println(Arrays.deepToString(tests[t]) + " " + (pass ? "PASS" : "FAIL"));
        }
        System.out.println(ok ? "ALL PASS" : "SOME FAILED");
    }
}

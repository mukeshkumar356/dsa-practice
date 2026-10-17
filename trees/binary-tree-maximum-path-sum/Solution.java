/**
 * Binary Tree Maximum Path Sum: a path is any sequence of connected nodes
 * (each node at most once, need not pass through the root). Return the
 * maximum sum of node values over all non-empty paths.
 *
 * Approach: post-order DFS. For each node, compute the best "downward" gain
 * (node value plus the better of its left/right downward gains, with negative
 * gains clamped to 0). While doing so, consider the path that bends at this
 * node: value + leftGain + rightGain, and update a global maximum.
 *
 * Time complexity:  O(n) - each node visited once
 * Space complexity: O(h) - recursion depth (h = tree height, O(n) worst case)
 */
public class Solution {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val; this.left = left; this.right = right;
        }
    }

    private int best;

    public int maxPathSum(TreeNode root) {
        best = Integer.MIN_VALUE;
        gain(root);
        return best;
    }

    // Max sum of a downward path starting at node (0 if node is null).
    private int gain(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, gain(node.left));
        int right = Math.max(0, gain(node.right));
        best = Math.max(best, node.val + left + right); // path bending at node
        return node.val + Math.max(left, right);        // extend to parent
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // [1,2,3] -> 2+1+3 = 6
        check(sol.maxPathSum(new TreeNode(1, new TreeNode(2), new TreeNode(3))), 6);
        // [-10,9,20,null,null,15,7] -> 15+20+7 = 42
        check(sol.maxPathSum(new TreeNode(-10, new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)))), 42);
        // edge: single negative node
        check(sol.maxPathSum(new TreeNode(-3)), -3);
        // edge: all negative, best is the single largest node
        check(sol.maxPathSum(new TreeNode(-2, new TreeNode(-1), null)), -1);
        // skewed chain with a negative in the middle: 5 -> -10 -> 20 => 20
        check(sol.maxPathSum(new TreeNode(5, new TreeNode(-10, new TreeNode(20), null), null)), 20);
        System.out.println("All tests passed");
    }

    private static void check(int actual, int expected) {
        if (actual != expected) throw new AssertionError("expected " + expected + " got " + actual);
        System.out.println("OK -> " + actual);
    }
}

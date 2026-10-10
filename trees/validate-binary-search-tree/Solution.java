/**
 * Given the root of a binary tree, determine whether it is a valid binary
 * search tree (every node's left subtree holds strictly smaller values and
 * its right subtree holds strictly larger values, recursively).
 *
 * Approach: DFS carrying an open interval (lo, hi) that each node's value must
 * fall inside. Going left tightens the upper bound to the node's value; going
 * right tightens the lower bound. This catches violations against ancestors,
 * not just against the direct parent. Bounds are Long to safely handle
 * Integer.MIN_VALUE / MAX_VALUE node values.
 *
 * Time complexity:  O(n) - each node is visited once
 * Space complexity: O(h) - recursion depth equals tree height (O(n) worst case)
 */
public class Solution {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long lo, long hi) {
        if (node == null) return true;
        if (node.val <= lo || node.val >= hi) return false;
        return validate(node.left, lo, node.val) && validate(node.right, node.val, hi);
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        // Valid:   2
        //         / \
        //        1   3
        TreeNode valid = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        System.out.println(s.isValidBST(valid) + " (expected true)");

        // Invalid: 5 with right child 4 that has children 3 and 6
        TreeNode invalid = new TreeNode(5, new TreeNode(1),
                new TreeNode(4, new TreeNode(3), new TreeNode(6)));
        System.out.println(s.isValidBST(invalid) + " (expected false)");

        // Tricky: 5 -> left 4 -> right 6 (6 > parent 4 but > ancestor 5 on the left side)
        TreeNode tricky = new TreeNode(5, new TreeNode(4, null, new TreeNode(6)), new TreeNode(7));
        System.out.println(s.isValidBST(tricky) + " (expected false)");

        // Edge: empty tree, single node, duplicates, Integer.MIN_VALUE
        System.out.println(s.isValidBST(null) + " (expected true)");
        System.out.println(s.isValidBST(new TreeNode(Integer.MIN_VALUE)) + " (expected true)");
        System.out.println(s.isValidBST(new TreeNode(2, new TreeNode(2), null)) + " (expected false)");
    }
}

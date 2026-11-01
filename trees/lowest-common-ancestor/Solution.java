/**
 * Given a binary tree and two nodes p and q that exist in it, find their
 * lowest common ancestor (LCA): the deepest node that has both p and q as
 * descendants (a node counts as a descendant of itself).
 *
 * Approach: post-order DFS. If the current node is null, p, or q, return it.
 * Otherwise recurse into both children. If both sides return non-null, p and q
 * lie in different subtrees, so the current node is the LCA. If only one side
 * is non-null, bubble that result up.
 *
 * Time complexity:  O(n) - each node is visited at most once
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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root; // p and q split here
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        //          3
        //        /   \
        //       5     1
        //      / \   / \
        //     6   2 0   8
        //        / \
        //       7   4
        TreeNode n7 = new TreeNode(7), n4 = new TreeNode(4);
        TreeNode n6 = new TreeNode(6), n2 = new TreeNode(2, n7, n4);
        TreeNode n0 = new TreeNode(0), n8 = new TreeNode(8);
        TreeNode n5 = new TreeNode(5, n6, n2), n1 = new TreeNode(1, n0, n8);
        TreeNode root = new TreeNode(3, n5, n1);

        System.out.println(s.lowestCommonAncestor(root, n5, n1).val + " (expected 3)");
        // One node is the ancestor of the other
        System.out.println(s.lowestCommonAncestor(root, n5, n4).val + " (expected 5)");
        // Deep nodes in the same subtree
        System.out.println(s.lowestCommonAncestor(root, n7, n4).val + " (expected 2)");
        // Edge: p == q
        System.out.println(s.lowestCommonAncestor(root, n8, n8).val + " (expected 8)");
        // Edge: two-node tree
        TreeNode a = new TreeNode(1), b = new TreeNode(2);
        a.left = b;
        System.out.println(s.lowestCommonAncestor(a, a, b).val + " (expected 1)");
    }
}

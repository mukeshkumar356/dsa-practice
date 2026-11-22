import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Kth Smallest Element in a BST: given the root of a binary search tree and
 * an integer k, return the kth smallest value (1-indexed) in the tree.
 *
 * Approach: an inorder traversal of a BST visits values in ascending order.
 * Do the traversal iteratively with an explicit stack (push all left
 * children, pop, count, move right) and stop as soon as the kth node is
 * popped - no need to visit the rest of the tree.
 *
 * Time complexity:  O(h + k) - descend to the leftmost node, then k pops
 * Space complexity: O(h) - stack holds at most one root-to-leaf path
 */
public class Solution {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {          // go as far left as possible
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();             // next smallest value
            if (--k == 0) return cur.val;  // kth visited node is the answer
            cur = cur.right;
        }
        throw new IllegalArgumentException("k is larger than the tree size");
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        // Tree 1:    3
        //           / \
        //          1   4
        //           \
        //            2
        TreeNode t1 = new TreeNode(3);
        t1.left = new TreeNode(1);
        t1.right = new TreeNode(4);
        t1.left.right = new TreeNode(2);
        check(s.kthSmallest(t1, 1), 1);
        check(s.kthSmallest(t1, 3), 3);

        // Tree 2:        5
        //               / \
        //              3   6
        //             / \
        //            2   4
        //           /
        //          1
        TreeNode t2 = new TreeNode(5);
        t2.left = new TreeNode(3);
        t2.right = new TreeNode(6);
        t2.left.left = new TreeNode(2);
        t2.left.right = new TreeNode(4);
        t2.left.left.left = new TreeNode(1);
        check(s.kthSmallest(t2, 3), 3);
        check(s.kthSmallest(t2, 6), 6);

        // Edge: single node
        check(s.kthSmallest(new TreeNode(7), 1), 7);
    }

    static int n = 0;
    static void check(int got, int expected) {
        System.out.println((got == expected ? "PASS" : "FAIL")
            + " test" + (++n) + " got=" + got + " expected=" + expected);
    }
}

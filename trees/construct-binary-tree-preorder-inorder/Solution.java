import java.util.HashMap;
import java.util.Map;

/**
 * Construct Binary Tree from Preorder and Inorder Traversal.
 * Given the preorder and inorder traversal arrays of a binary tree with
 * unique values, rebuild and return the tree.
 *
 * Approach: the first preorder element is the root. Its position in the inorder
 * array splits the remaining nodes into the left and right subtrees. Recurse on
 * both halves, consuming preorder left to right. A hash map from value to
 * inorder index makes each root lookup O(1).
 *
 * Time complexity:  O(n) - each node is created once, O(1) lookup per node
 * Space complexity: O(n) - hash map, plus O(h) recursion stack
 */
public class Solution {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private int preIdx;
    private Map<Integer, Integer> inIndex;
    private int[] preorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.preIdx = 0;
        this.inIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inIndex.put(inorder[i], i);
        return build(0, inorder.length - 1);
    }

    // Builds the subtree whose nodes occupy inorder[lo..hi].
    private TreeNode build(int lo, int hi) {
        if (lo > hi) return null;
        TreeNode root = new TreeNode(preorder[preIdx++]);
        int mid = inIndex.get(root.val);
        root.left = build(lo, mid - 1);   // must go left first to match preorder
        root.right = build(mid + 1, hi);
        return root;
    }

    private static void preorderOf(TreeNode n, StringBuilder sb) {
        if (n == null) { sb.append("null "); return; }
        sb.append(n.val).append(' ');
        preorderOf(n.left, sb);
        preorderOf(n.right, sb);
    }

    private static String serialize(TreeNode n) {
        StringBuilder sb = new StringBuilder();
        preorderOf(n, sb);
        return sb.toString().trim();
    }

    private static void check(String name, String expected, String actual) {
        boolean ok = expected.equals(actual);
        System.out.println((ok ? "PASS " : "FAIL ") + name + ": " + actual);
        if (!ok) System.exit(1);
    }

    public static void main(String[] args) {
        // Example: tree 3 / (9, 20 / (15, 7))
        check("classic", "3 9 null null 20 15 null null 7 null null",
                serialize(new Solution().buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7})));
        // Edge: single node
        check("single", "-1 null null",
                serialize(new Solution().buildTree(new int[]{-1}, new int[]{-1})));
        // Edge: left-skewed
        check("left-skewed", "1 2 3 null null null null",
                serialize(new Solution().buildTree(new int[]{1, 2, 3}, new int[]{3, 2, 1})));
        // Edge: right-skewed
        check("right-skewed", "1 null 2 null 3 null null",
                serialize(new Solution().buildTree(new int[]{1, 2, 3}, new int[]{1, 2, 3})));
        // Edge: empty
        check("empty", "null", serialize(new Solution().buildTree(new int[]{}, new int[]{})));
    }
}

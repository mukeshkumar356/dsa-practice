/**
 * Design a data structure that supports addWord(word) and search(word), where
 * search may contain '.' as a wildcard matching any single letter.
 *
 * Approach: store words in a 26-ary trie. addWord walks/creates one node per
 * letter and marks the last node as a word end. search walks the trie; on a
 * normal letter it follows that child, on '.' it tries every existing child
 * recursively (DFS) and succeeds if any branch matches the remaining suffix.
 *
 * Time complexity:  addWord O(L); search O(L) without wildcards, worst case
 *                   O(26^w * L) with w wildcards (bounded by trie size)
 * Space complexity: O(total characters inserted) for the trie
 */
public class Solution {
    private static class Node {
        Node[] next = new Node[26];
        boolean isEnd;
    }

    private final Node root = new Node();

    public void addWord(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (cur.next[i] == null) cur.next[i] = new Node();
            cur = cur.next[i];
        }
        cur.isEnd = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String w, int pos, Node node) {
        if (node == null) return false;
        if (pos == w.length()) return node.isEnd;
        char c = w.charAt(pos);
        if (c == '.') {
            // wildcard: try every possible child
            for (Node child : node.next) {
                if (child != null && dfs(w, pos + 1, child)) return true;
            }
            return false;
        }
        return dfs(w, pos + 1, node.next[c - 'a']);
    }

    public static void main(String[] args) {
        Solution d = new Solution();
        d.addWord("bad");
        d.addWord("dad");
        d.addWord("mad");
        check(d.search("pad"), false);
        check(d.search("bad"), true);
        check(d.search(".ad"), true);
        check(d.search("b.."), true);
        check(d.search("..."), true);
        check(d.search("...."), false);   // longer than any word
        check(d.search("ba"), false);     // prefix only, not a word
        check(d.search(".."), false);
        check(new Solution().search("a"), false);  // empty structure
        System.out.println("All tests passed");
    }

    private static void check(boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError("expected " + expected + " but got " + actual);
        }
    }
}

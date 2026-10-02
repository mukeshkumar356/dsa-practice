/**
 * A trie (prefix tree) is a tree data structure used to efficiently store
 * and retrieve keys from a set of strings. Implement the Trie class:
 *
 *   - insert(String word)       - inserts the string word into the trie
 *   - search(String word)       - returns true if word is in the trie
 *                                 (full word match, as an inserted word)
 *   - startsWith(String prefix) - returns true if any previously inserted
 *                                 word has prefix as a prefix
 *
 * Approach: each node holds up to 26 child pointers (one per lowercase
 * letter) plus an isEnd flag marking "a word ends here". insert walks the
 * tree one character at a time, creating child nodes as needed. search and
 * startsWith both walk the tree the same way via a shared helper, and the
 * only difference between them is whether the final node must also have
 * isEnd set (search) or merely exist (startsWith).
 *
 * Time complexity:  O(L) per operation, where L is the length of the
 *                    word/prefix being inserted or looked up
 * Space complexity: O(N * L) in the worst case, where N is the number of
 *                    inserted words and L their average length (no shared
 *                    prefixes)
 */
public class Solution {

    static class Trie {
        private final Trie[] children = new Trie[26];
        private boolean isEnd = false;

        public void insert(String word) {
            Trie node = this;
            for (char c : word.toCharArray()) {
                int i = c - 'a';
                if (node.children[i] == null) {
                    node.children[i] = new Trie();
                }
                node = node.children[i];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = find(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            return find(prefix) != null;
        }

        private Trie find(String s) {
            Trie node = this;
            for (char c : s.toCharArray()) {
                int i = c - 'a';
                if (node.children[i] == null) {
                    return null;
                }
                node = node.children[i];
            }
            return node;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // true
        System.out.println(trie.search("app"));      // false - "app" was never inserted as a full word
        System.out.println(trie.startsWith("app"));  // true
        trie.insert("app");
        System.out.println(trie.search("app"));      // true - now it has been inserted
    }
}

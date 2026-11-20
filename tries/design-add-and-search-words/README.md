# Design Add and Search Words Data Structure

**Topic:** Tries
**Difficulty:** Medium

## Problem

Design a data structure that supports `addWord(word)` and `search(word)`. `search` may contain `.` characters, each of which matches any single letter. Return whether any previously added word matches.

**Example**
```
addWord("bad"); addWord("dad"); addWord("mad");
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
```

## Approach

Store words in a 26-ary trie with an `isEnd` flag. `addWord` creates one node per letter. `search` descends the trie; a normal letter follows one child, while `.` recursively tries every existing child and succeeds if any of them matches the rest of the pattern. A match requires reaching the end of the pattern on a node flagged `isEnd`, so prefixes like `"ba"` do not count.

## Complexity

- **Time:** `addWord` is `O(L)`; `search` is `O(L)` without wildcards and worst case `O(26^w * L)` with `w` wildcards (bounded by the trie size)
- **Space:** `O(N)` total characters stored

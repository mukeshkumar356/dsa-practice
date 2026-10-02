# Implement Trie (Prefix Tree)

**Topic:** Tries
**Difficulty:** Medium

## Problem

Implement a trie with `insert`, `search`, and `startsWith`:

- `insert(word)` - adds `word` to the trie
- `search(word)` - returns `true` only if `word` was previously inserted as a complete word
- `startsWith(prefix)` - returns `true` if any inserted word starts with `prefix`

**Example**
```
insert("apple")
search("apple")     -> true
search("app")       -> false  (never inserted as a full word)
startsWith("app")   -> true
insert("app")
search("app")       -> true   (now it has been inserted)
```

## Approach

Each trie node holds 26 child slots (one per lowercase letter) and an `isEnd` flag. `insert` walks the tree one character at a time, creating a child node whenever one doesn't already exist, then marks `isEnd` on the final node.

`search` and `startsWith` share the same tree-walking helper (`find`) - the only difference is that `search` also requires the node it lands on to have `isEnd` set, while `startsWith` only needs the path to exist. This is the key distinction the problem is testing: a prefix existing in the tree doesn't mean it was ever inserted as a whole word.

## Complexity

- **Time:** `O(L)` per operation, where `L` is the length of the word/prefix
- **Space:** `O(N * L)` worst case, where `N` is the number of inserted words with no shared prefixes between them

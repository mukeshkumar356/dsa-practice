# Lowest Common Ancestor of a Binary Tree

**Topic:** Trees (DFS)
**Difficulty:** Medium

## Problem

Given a binary tree and two nodes `p` and `q` in it, return their lowest common ancestor: the deepest node that has both as descendants (a node may be a descendant of itself).

**Example**
```
        3
      /   \
     5     1
    / \   / \
   6   2 0   8
      / \
     7   4

LCA(5, 1) = 3
LCA(5, 4) = 5   (a node can be its own descendant)
```

## Approach

Post-order DFS:

1. If the node is `null`, `p`, or `q`, return it.
2. Search the left and right subtrees.
3. If both searches return a node, `p` and `q` are on different sides, so the current node is the LCA.
4. Otherwise return whichever side is non-null (that is either the LCA found below, or the single target found).

## Complexity

- **Time:** `O(n)` — each node visited at most once
- **Space:** `O(h)` — recursion stack (`O(n)` for a skewed tree)

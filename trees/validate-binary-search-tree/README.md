# Validate Binary Search Tree

**Topic:** Trees (DFS)
**Difficulty:** Medium

## Problem

Given the root of a binary tree, determine whether it is a valid binary search tree: for every node, all values in its left subtree are strictly less than the node, and all values in its right subtree are strictly greater.

**Example**
```
Input:   2        Output: true
        / \
       1   3

Input:   5        Output: false  (4's subtree contains 3, which is < 5)
        / \
       1   4
          / \
         3   6
```

## Approach

Comparing a node only with its parent is not enough — a deep descendant must respect *every* ancestor. So pass down an open interval `(lo, hi)`:

1. The root may take any value: `(-inf, +inf)`.
2. Moving left, the node's value becomes the new upper bound; moving right, it becomes the new lower bound.
3. If any node's value lies outside its interval, the tree is invalid.

Bounds are stored as `long` so nodes holding `Integer.MIN_VALUE` / `Integer.MAX_VALUE` are handled correctly.

## Complexity

- **Time:** `O(n)` — each node is visited once
- **Space:** `O(h)` — recursion stack, where `h` is the tree height (`O(n)` for a skewed tree)

# Construct Binary Tree from Preorder and Inorder Traversal

**Topic:** Trees (Divide & Conquer)
**Difficulty:** Medium

## Problem

Given two integer arrays `preorder` and `inorder` — the preorder and inorder traversals of the same binary tree with unique values — construct and return the tree.

**Example**
```
preorder = [3, 9, 20, 15, 7]
inorder  = [9, 3, 15, 20, 7]

      3
     / \
    9   20
       /  \
      15   7
```

## Approach

1. The first unused element of `preorder` is always the root of the current subtree.
2. Find that value in `inorder`: everything to its left is the left subtree, everything to its right is the right subtree.
3. Recurse on the left range first, then the right range (preorder visits left before right, so the pointer into `preorder` advances correctly).
4. Store each value's inorder index in a hash map so step 2 is `O(1)` instead of a linear scan.

## Complexity

- **Time:** `O(n)` — each node is created once
- **Space:** `O(n)` — hash map, plus `O(h)` recursion stack

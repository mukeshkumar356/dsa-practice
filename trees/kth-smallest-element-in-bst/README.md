# Kth Smallest Element in a BST

**Topic:** Trees (Binary Search Tree)
**Difficulty:** Medium

## Problem

Given the root of a binary search tree and an integer `k`, return the `k`th smallest value (1-indexed) of all node values.

**Example**
```
root = [3,1,4,null,2], k = 1  ->  1
root = [5,3,6,2,4,null,null,1], k = 3  ->  3
```

## Approach

Inorder traversal of a BST yields values in ascending order. Traverse iteratively with a stack: push the left spine, pop a node, decrement `k`, and return when it hits 0; otherwise continue with the popped node's right child. Stops early after `k` nodes.

## Complexity

- **Time:** `O(h + k)`
- **Space:** `O(h)`

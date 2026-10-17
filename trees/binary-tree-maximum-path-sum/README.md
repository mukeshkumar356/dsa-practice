# Binary Tree Maximum Path Sum

**Topic:** Trees (DFS)
**Difficulty:** Hard

## Problem

A path in a binary tree is a sequence of connected nodes where each node appears at most once; it need not pass through the root. Return the maximum sum of node values over any non-empty path.

**Example**
```
Input:  root = [-10,9,20,null,null,15,7]
Output: 42   (15 -> 20 -> 7)
```

## Approach

Post-order DFS. For each node, `gain(node)` is the best downward path sum starting at that node, using the better child gain (negative gains are clamped to 0).

1. `left = max(0, gain(node.left))`, `right = max(0, gain(node.right))`.
2. The best path bending at this node is `node.val + left + right`; update a global maximum.
3. Return `node.val + max(left, right)` so the parent can extend the path.

## Complexity

- **Time:** `O(n)`
- **Space:** `O(h)` recursion stack

# Binary Tree Level Order Traversal

**Topic:** Trees (BFS)
**Difficulty:** Medium

## Problem

Given the root of a binary tree, return the values of its nodes level by level, from top to bottom, left to right within each level.

**Example**
```
Input:
     3
    / \
   9   20
       / \
      15  7

Output: [[3], [9, 20], [15, 7]]
```

## Approach

This is a textbook Breadth-First Search. A queue naturally processes nodes level by level if we track how many nodes belong to the *current* level before we start adding their children.

1. Push the root into the queue.
2. While the queue isn't empty, record its current size (`levelSize`) — this is exactly how many nodes belong to this level.
3. Pop exactly `levelSize` nodes, collecting their values and pushing their children for the next round.
4. Repeat until the queue is empty.

Capturing `levelSize` before the inner loop is the key detail — without it, children added mid-level would get processed as if they belonged to the current level.

## Complexity

- **Time:** `O(n)` — every node is visited exactly once
- **Space:** `O(n)` — in the worst case (a completely full last level), the queue holds up to `n/2` nodes at once

# Redundant Connection

**Topic:** Union-Find (Disjoint Set Union)
**Difficulty:** Medium

## Problem

A tree with `n` nodes (labeled `1..n`) had one extra edge added. Given the resulting graph as `edges`, return the edge that can be removed so the graph is a tree again. If there are multiple answers, return the one that occurs last in the input.

**Example**
```
Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
```

## Approach

Process edges in order with a disjoint set. If both endpoints of an edge already have the same root, the edge closes a cycle and is the redundant one. Path compression and union by rank keep each operation nearly constant time.

## Complexity

- **Time:** `O(n * alpha(n))`
- **Space:** `O(n)`

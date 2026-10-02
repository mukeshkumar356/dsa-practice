# Number of Provinces

**Topic:** Union-Find (Disjoint Set Union)
**Difficulty:** Medium

## Problem

There are `n` cities, some connected directly, some not. A province is a group of directly or indirectly connected cities with no other cities outside the group. Given an `n x n` adjacency matrix `isConnected`, return the total number of provinces.

**Example**
```
Input:
isConnected = [[1,1,0],[1,1,0],[0,0,1]]

Output: 2
```

## Approach

Treat each city as its own set initially. Walk through every pair `(i, j)` in the matrix - whenever `isConnected[i][j] == 1`, union their sets. Once every connection has been processed, the number of distinct root nodes left is the number of provinces.

Path compression (flattening the tree on every `find`) and union by rank (always attaching the smaller tree under the bigger one) keep both operations close to constant time, so the matrix scan dominates the overall cost.

## Complexity

- **Time:** `O(n^2 * alpha(n))` — the `n x n` matrix scan dominates; `alpha(n)` (inverse Ackermann) from the union-find operations is effectively constant for any realistic input
- **Space:** `O(n)` — parent and rank arrays, one entry per city

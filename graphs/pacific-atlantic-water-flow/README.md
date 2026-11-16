# Pacific Atlantic Water Flow

**Topic:** Graphs (DFS on a grid, reverse search)
**Difficulty:** Medium

## Problem

Given an `m x n` grid of heights, water flows from a cell to any 4-directional neighbor with height less than or equal to its own. The Pacific touches the top and left edges; the Atlantic touches the bottom and right edges. Return all cells from which water can flow to both oceans.

**Example**
```
Input:  [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
```

## Approach

Checking every cell forward is wasteful, so reverse the flow:

1. Start a DFS from every Pacific border cell, moving only to neighbors that are equal or higher. Everything reached can flow to the Pacific.
2. Do the same from the Atlantic border cells.
3. Cells marked by both searches are the answer.

## Complexity

- **Time:** `O(m * n)` — each cell is visited at most once per ocean
- **Space:** `O(m * n)` — visited grids and the recursion stack

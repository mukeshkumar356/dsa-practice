# Number of Islands

**Topic:** Graphs (DFS/Flood Fill)
**Difficulty:** Medium

## Problem

Given an `m x n` grid of `'1'`s (land) and `'0'`s (water), count the number of islands. An island is a group of `'1'`s connected horizontally or vertically.

**Example**
```
Input:
11110
11010
11000
00000

Output: 1
```

## Approach

Scan the grid cell by cell. Whenever an unvisited `'1'` is found, it's the start of a new island - increment the counter and flood-fill outward from that cell in all four directions, turning every connected `'1'` into a `'0'` so it's never counted again.

This "sink the land as you visit it" trick avoids needing a separate `visited` matrix - the grid itself doubles as the visited-tracking structure.

## Complexity

- **Time:** `O(rows * cols)` — each cell is examined by the outer loop once, and touched at most once more during a flood fill
- **Space:** `O(rows * cols)` — worst case call stack depth if the whole grid is a single connected island (could be reduced with an iterative stack-based DFS/BFS if recursion depth is a concern for very large grids)

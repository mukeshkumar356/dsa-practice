# Rotting Oranges

**Topic:** Graphs (multi-source BFS)
**Difficulty:** Medium

## Problem

Given an `m x n` grid where `0` is an empty cell, `1` is a fresh orange and `2` is a rotten orange, every minute any fresh orange 4-directionally adjacent to a rotten one becomes rotten. Return the minimum number of minutes until no fresh orange remains, or `-1` if that is impossible.

**Example**
```
Input:  [[2,1,1],
         [1,1,0],
         [0,1,1]]
Output: 4

Input:  [[2,1,1],
         [0,1,1],
         [1,0,1]]
Output: -1   (the bottom-left orange is never reached)
```

## Approach

Rot spreads simultaneously from every rotten orange, which is exactly a BFS started from *all* sources at once:

1. Scan the grid, enqueueing every rotten orange and counting fresh ones.
2. Process the queue one level at a time; each level equals one minute.
3. Infect each fresh neighbor, decrement the fresh count and enqueue it.
4. After BFS, if fresh oranges remain they were unreachable, so return `-1`; otherwise return the elapsed minutes.

## Complexity

- **Time:** `O(m * n)` — each cell is enqueued at most once
- **Space:** `O(m * n)` — queue size in the worst case

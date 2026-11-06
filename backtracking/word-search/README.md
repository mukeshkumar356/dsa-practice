# Word Search

**Topic:** Backtracking
**Difficulty:** Medium

## Problem

Given an `m x n` grid of characters and a string `word`, return `true` if `word` exists in the grid. The word can be constructed from letters of sequentially adjacent cells (horizontally or vertically); the same cell may not be used more than once.

**Example**
```
board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
word = "ABCCED"  ->  true
word = "ABCB"    ->  false
```

## Approach

DFS with backtracking from every cell:

1. If all characters are matched, return `true`.
2. If out of bounds or the cell doesn't match the current character, return `false`.
3. Mark the cell visited by overwriting it with `#`, explore the 4 neighbours for the next character, then restore the cell so other paths can use it.

## Complexity

- **Time:** `O(m * n * 3^L)` where `L = word.length()`
- **Space:** `O(L)` — recursion depth

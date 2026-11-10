# Rotate Image

**Topic:** Arrays (Matrix)
**Difficulty:** Medium

## Problem

Given an `n x n` 2D matrix representing an image, rotate the image by 90 degrees clockwise, in-place.

**Example**
```
[[1,2,3],[4,5,6],[7,8,9]]  ->  [[7,4,1],[8,5,2],[9,6,3]]
```

## Approach

A clockwise 90° rotation is a transpose followed by reversing each row:

1. Transpose: swap `m[i][j]` with `m[j][i]` for `j > i`.
2. Reverse every row.

## Complexity

- **Time:** `O(n^2)`
- **Space:** `O(1)` — in-place

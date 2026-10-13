# Trapping Rain Water

**Topic:** Two Pointers
**Difficulty:** Hard

## Problem

Given `n` non-negative integers representing an elevation map where each bar has width 1, compute how much water it can trap after raining.

**Example**
```
Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
```

## Approach

The water above bar `i` is `min(maxLeft(i), maxRight(i)) - height[i]`. Instead of precomputing prefix/suffix maxima, use two pointers from both ends with running `leftMax` and `rightMax`.

If `height[left] < height[right]`, the right side holds a wall at least as tall as `height[left]`, so the left side is the limiting one and its water level is exactly `leftMax`. Process the left bar and advance; otherwise do the symmetric step on the right.

## Complexity

- **Time:** `O(n)` — single pass
- **Space:** `O(1)` — a few variables

# Largest Rectangle in Histogram

**Topic:** Stacks (Monotonic Stack)
**Difficulty:** Hard

## Problem

Given an array `heights` where each bar has width 1, return the area of the largest rectangle that can be formed within the histogram.

**Example**
```
Input:  [2, 1, 5, 6, 2, 3]
Output: 10   (bars of height 5 and 6 -> 5 * 2)
```

## Approach

Keep a stack of indices whose heights are increasing from bottom to top.
1. For each bar `i` (plus a sentinel height `0` after the last bar), while the current height is smaller than the height at the top index, pop that bar `h`.
2. The popped bar's rectangle spans from `stack.peek() + 1` (or `0` if the stack is empty) to `i - 1`, so its area is `h * (i - left)`.
3. Track the maximum area, then push `i`.

## Complexity

- **Time:** `O(n)` — every index is pushed and popped at most once
- **Space:** `O(n)` — the stack

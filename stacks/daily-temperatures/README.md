# Daily Temperatures

**Topic:** Stacks (Monotonic Stack)
**Difficulty:** Medium

## Problem

Given an array `temperatures`, return an array `answer` where `answer[i]` is the number of days after day `i` until a warmer temperature. If there is none, `answer[i] = 0`.

**Example**
```
Input:  [73, 74, 75, 71, 69, 72, 76, 73]
Output: [1, 1, 4, 2, 1, 1, 0, 0]
```

## Approach

Keep a stack of indices whose temperatures are strictly decreasing from bottom to top (a monotonic stack).
1. For each day `i`, while `temps[i]` is greater than the temperature at the top index, pop it and record `i - top` as its wait.
2. Push `i`.
3. Indices still on the stack at the end never find a warmer day, so they keep `0`.

## Complexity

- **Time:** `O(n)` — every index is pushed and popped at most once
- **Space:** `O(n)` — the stack

# Product of Array Except Self

**Topic:** Arrays & Hashing (Prefix/Suffix Products)
**Difficulty:** Medium

## Problem

Given an integer array `nums`, return an array `answer` where `answer[i]` is the product of all elements of `nums` except `nums[i]`. Solve it in `O(n)` time without using division.

**Example**
```
Input:  [1, 2, 3, 4]
Output: [24, 12, 8, 6]
```

## Approach

1. Left-to-right pass: `answer[i]` = product of everything to the left of `i`.
2. Right-to-left pass: keep a running product of everything to the right of `i` and multiply it into `answer[i]`.

No division is needed, so zeros are handled naturally.

## Complexity

- **Time:** `O(n)` — two passes
- **Space:** `O(1)` extra (output array not counted)

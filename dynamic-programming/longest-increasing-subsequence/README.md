# Longest Increasing Subsequence

**Topic:** Dynamic Programming
**Difficulty:** Medium

## Problem

Given an integer array `nums`, return the length of the longest strictly increasing subsequence (elements need not be contiguous).

**Example**
```
Input:  nums = [10, 9, 2, 5, 3, 7, 101, 18]
Output: 4     // [2, 3, 7, 101]
```

## Approach

- Maintain `tails[i]`, the smallest possible tail of an increasing subsequence of length `i + 1`.
- `tails` is always sorted, so for each number binary search the first tail `>= x` and replace it; if none exists, append.
- Replacing keeps tails as small as possible, leaving the most room for future extensions.
- The size of `tails` at the end is the answer (the array itself is not necessarily a valid subsequence).

## Complexity

- **Time:** `O(n log n)`
- **Space:** `O(n)`

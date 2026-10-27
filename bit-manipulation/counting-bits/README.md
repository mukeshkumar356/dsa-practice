# Counting Bits

**Topic:** Bit Manipulation
**Difficulty:** Easy

## Problem

Given a non-negative integer `n`, return an array `ans` of length `n + 1` such that `ans[i]` is the number of `1` bits in the binary representation of `i`.

**Example**
```
Input: n = 5
Output: [0, 1, 1, 2, 1, 2]
```

## Approach

Counting bits of each number separately costs `O(n log n)`. Instead, reuse earlier answers: `i >> 1` is `i` with its lowest bit removed, and it is smaller than `i`, so its count is already known. The only thing missing is the dropped bit, which is `i & 1`.

```
ans[i] = ans[i >> 1] + (i & 1)
```

## Complexity

- **Time:** `O(n)` — constant work per number
- **Space:** `O(1)` extra — only the required output array

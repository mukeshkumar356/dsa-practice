# Permutations

**Topic:** Backtracking
**Difficulty:** Medium

## Problem

Given an array of distinct integers, return all possible permutations, in any order.

**Example**
```
Input: [1, 2, 3]
Output: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
```

## Approach

Build each permutation one position at a time:

1. If the current list has `n` elements, it is a complete permutation — copy it into the result.
2. Otherwise, try every element not yet used at this position: mark it used, add it, recurse, then **remove it and unmark it** so the next branch can reuse it.

A `used` boolean array makes the "already in the current permutation" check O(1).

## Complexity

- **Time:** `O(n * n!)` — `n!` permutations, each copied in `O(n)`
- **Space:** `O(n)` — recursion depth, current list and used array, excluding the output

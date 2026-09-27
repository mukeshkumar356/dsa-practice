# Subsets

**Topic:** Backtracking
**Difficulty:** Medium

## Problem

Given an array of unique integers, return all possible subsets (the power set) — including the empty set and the array itself.

**Example**
```
Input: [1, 2, 3]
Output: [[], [1], [1,2], [1,2,3], [1,3], [2], [2,3], [3]]
```

## Approach

At every element, there are exactly two choices: include it in the current subset, or skip it. Backtracking explores both choices systematically:

1. Record the current subset as a valid result — every partial subset built along the way (not just complete leaves) is itself a valid answer.
2. For each remaining element starting from `index`, add it to the current subset, recurse forward, then **remove it again** before trying the next element. This "undo" step is what lets the same list get reused across all branches instead of allocating a new list at every node.

Starting the inner loop from `index` (not `0`) is what prevents duplicate subsets like `[1,2]` and `[2,1]` from both being generated.

## Complexity

- **Time:** `O(n * 2^n)` — there are `2^n` subsets total, and copying each one into the result costs up to `O(n)`
- **Space:** `O(n)` — the recursion depth and the current subset's size, excluding the output list itself

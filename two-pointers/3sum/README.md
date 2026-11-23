# 3Sum

**Topic:** Two Pointers
**Difficulty:** Medium

## Problem

Given an integer array `nums`, return all unique triplets `[nums[i], nums[j], nums[k]]` (distinct indices) that sum to `0`. The result must not contain duplicate triplets.

**Example**
```
Input: [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
```

## Approach

Sort the array. Fix the first element `i`, then search the rest with two pointers `lo`/`hi` for a pair summing to `-nums[i]`: if the sum is too small advance `lo`, if too large retreat `hi`. On a match, record the triplet and move both pointers, skipping equal values so no triplet repeats. Also skip duplicate values of `i`, and stop once `nums[i] > 0` (sorted, so no zero-sum is possible).

## Complexity

- **Time:** `O(n^2)` — sorting is `O(n log n)`, then one linear two-pointer pass per `i`
- **Space:** `O(1)` extra, ignoring the output and sort internals

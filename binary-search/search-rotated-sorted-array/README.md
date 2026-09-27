# Search in Rotated Sorted Array

**Topic:** Binary Search
**Difficulty:** Medium

## Problem

An ascending array has been rotated at an unknown pivot (e.g. `[0,1,2,4,5,6,7]` becomes `[4,5,6,7,0,1,2]`). Given the rotated array and a `target`, return its index, or `-1` if not found — in `O(log n)`.

**Example**
```
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
```

## Approach

A plain binary search assumes the whole array is sorted, which isn't true here. But a useful fact holds at every step: **at least one of the two halves around `mid` is always properly sorted**, even in a rotated array.

So at each step:
1. Compute `mid`. If it's the target, done.
2. Figure out which half (`left..mid` or `mid..right`) is sorted by comparing `nums[left]` and `nums[mid]`.
3. If the target's value falls within that sorted half's range, search there. Otherwise, it must be in the other (unsorted-looking, but still rotated-sorted) half — search that one instead.

This keeps discarding half the array each time, just like ordinary binary search.

## Complexity

- **Time:** `O(log n)` — the search space is halved every iteration
- **Space:** `O(1)` — iterative approach, only a few index variables

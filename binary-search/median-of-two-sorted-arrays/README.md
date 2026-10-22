# Median of Two Sorted Arrays

**Topic:** Binary Search
**Difficulty:** Hard

## Problem

Given two sorted arrays `nums1` and `nums2`, return the median of the two combined sorted arrays. The overall runtime must be `O(log(m + n))`.

**Example**
```
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.5
```

## Approach

Merging works in `O(m + n)` but is too slow. Instead, binary search for the correct *partition* of the smaller array `A`:

1. Take `i` elements from `A` and `j = (m + n + 1) / 2 - i` elements from `B` as the combined left half.
2. The split is correct when `A[i-1] <= B[j]` and `B[j-1] <= A[i]` — everything on the left is no larger than everything on the right.
3. If `A[i-1] > B[j]`, `i` is too large, so search lower; otherwise search higher.
4. Out-of-range neighbours are treated as `-inf` / `+inf`, which handles empty arrays and edge partitions.
5. Odd total length: the median is the max of the left half. Even: the average of the left max and the right min.

Searching the smaller array keeps `j` non-negative and gives the `log(min(m, n))` bound.

## Complexity

- **Time:** `O(log(min(m, n)))`
- **Space:** `O(1)`

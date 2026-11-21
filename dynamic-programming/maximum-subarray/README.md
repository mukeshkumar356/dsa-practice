# Maximum Subarray

**Topic:** Dynamic Programming (Arrays)
**Difficulty:** Medium

## Problem

Given an integer array `nums`, find the contiguous subarray (at least one element) with the largest sum and return its sum.

**Example**
```
[-2,1,-3,4,-1,2,1,-5,4]  ->  6   (subarray [4,-1,2,1])
```

## Approach

Kadane's algorithm. Track `cur`, the best sum of a subarray ending at the current index: `cur = max(nums[i], cur + nums[i])`. The answer is the largest `cur` seen. Initializing from `nums[0]` handles all-negative arrays.

## Complexity

- **Time:** `O(n)`
- **Space:** `O(1)`

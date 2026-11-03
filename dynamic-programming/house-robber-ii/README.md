# House Robber II

**Topic:** Dynamic Programming
**Difficulty:** Medium

## Problem

Houses are arranged in a circle, each with some amount of money. Adjacent houses cannot both be robbed (the first and last houses are adjacent). Return the maximum amount you can rob.

**Example**
```
Input: nums = [2,3,2]
Output: 3
```

## Approach

House 0 and house n-1 cannot both be robbed, so run the linear House Robber DP twice: on `nums[0..n-2]` and on `nums[1..n-1]`, and return the larger result. The linear DP uses `cur = max(prev1, prev2 + nums[i])` with two rolling variables.

## Complexity

- **Time:** `O(n)`
- **Space:** `O(1)`

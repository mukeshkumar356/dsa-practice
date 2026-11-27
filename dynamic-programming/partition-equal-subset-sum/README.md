# Partition Equal Subset Sum

**Topic:** Dynamic Programming (0/1 Knapsack)
**Difficulty:** Medium

## Problem

Given an array `nums` of positive integers, return `true` if it can be partitioned into two subsets with equal sum.

**Example**
```
[1,5,11,5]  ->  true   ([1,5,5] and [11])
[1,2,3,5]   ->  false
```

## Approach

If the total is odd, the answer is `false`. Otherwise look for a subset summing to `total / 2`. `dp[s]` is true when some subset of the numbers processed so far sums to `s`. For each number, iterate `s` from `target` down to `num` (descending so each number is used once) and set `dp[s] |= dp[s - num]`.

## Complexity

- **Time:** `O(n * sum/2)`
- **Space:** `O(sum/2)`

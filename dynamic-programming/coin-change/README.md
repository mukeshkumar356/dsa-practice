# Coin Change

**Topic:** Dynamic Programming
**Difficulty:** Medium

## Problem

Given an array `coins` of denominations and an integer `amount`, return the fewest coins needed to make up `amount`, or `-1` if it can't be done. You have an unlimited supply of each coin.

**Example**
```
Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
```

## Approach

Let `dp[a]` be the minimum number of coins to make amount `a`.

- Base case: `dp[0] = 0`.
- For each amount `a` from 1 to `amount`, try each coin `c <= a`: `dp[a] = min(dp[a], dp[a - c] + 1)`.
- Initialize every other entry to `amount + 1` (an "infinity" larger than any real answer). If `dp[amount]` is still that value, no combination works, so return `-1`.

## Complexity

- **Time:** `O(amount * n)` for `n` coin types
- **Space:** `O(amount)`

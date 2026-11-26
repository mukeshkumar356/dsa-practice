# Best Time to Buy and Sell Stock

**Topic:** Arrays & Hashing
**Difficulty:** Easy

## Problem

Given an array `prices` where `prices[i]` is the price on day `i`, pick one day to buy and a later day to sell to maximize profit. Return the max profit, or `0` if none is possible.

**Example**
```
prices = [7,1,5,3,6,4]  ->  5   (buy at 1, sell at 6)
prices = [7,6,4,3,1]    ->  0
```

## Approach

Scan once, tracking the minimum price so far. For each price, the best profit from selling that day is `price - minSoFar`; keep the maximum.

## Complexity

- **Time:** `O(n)`
- **Space:** `O(1)`

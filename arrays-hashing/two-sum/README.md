# Two Sum

**Topic:** Arrays & Hashing
**Difficulty:** Easy

## Problem

Given an array of integers `nums` and an integer `target`, return the indices of the two numbers such that they add up to `target`. Each input has exactly one solution, and you may not use the same element twice.

**Example**
```
Input: nums = [2, 7, 11, 15], target = 9
Output: [0, 1]
Explanation: nums[0] + nums[1] = 2 + 7 = 9
```

## Approach

A brute-force check of every pair is `O(n^2)`. Instead, walk the array once and use a hashmap to remember every number we've already seen along with its index.

For each new number, compute its complement (`target - number`). If that complement is already in the map, we've found our pair immediately. Otherwise, add the current number to the map and keep going.

## Complexity

- **Time:** `O(n)` — one pass through the array
- **Space:** `O(n)` — the hashmap holds up to `n` entries in the worst case

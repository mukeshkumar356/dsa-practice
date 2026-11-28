# Missing Number

**Topic:** Bit Manipulation
**Difficulty:** Easy

## Problem

Given an array `nums` of `n` distinct numbers from the range `[0, n]`, return the only number in the range that is missing.

**Example**
```
nums = [3,0,1]  ->  2
```

## Approach

XOR every index `0..n` with every element. Each present number appears twice (once as an index, once as a value) and cancels out; the missing number appears once and remains. Unlike the sum formula, this cannot overflow.

## Complexity

- **Time:** `O(n)`
- **Space:** `O(1)`

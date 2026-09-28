# Single Number

**Topic:** Bit Manipulation
**Difficulty:** Easy

## Problem

Given a non-empty array where every element appears exactly twice except for one, find that single element. Must run in linear time without extra memory.

**Example**
```
Input: [4, 1, 2, 1, 2]
Output: 4
```

## Approach

A hashmap counting occurrences would work but needs `O(n)` extra space, which the problem rules out. The XOR trick avoids that entirely.

XOR has three properties that make this click:
- `a ^ a = 0` — any number XORed with itself cancels out
- `a ^ 0 = a` — XOR with zero changes nothing
- XOR is commutative/associative — order doesn't matter

So if we XOR every number in the array together, every pair cancels itself down to `0`, and the only thing left standing is the number that had no pair to cancel with.

## Complexity

- **Time:** `O(n)` — one pass through the array
- **Space:** `O(1)` — a single accumulator variable

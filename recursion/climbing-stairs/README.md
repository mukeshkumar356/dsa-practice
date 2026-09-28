# Climbing Stairs

**Topic:** Recursion / Memoization
**Difficulty:** Easy

## Problem

You're climbing a staircase of `n` steps. Each move is either 1 or 2 steps. How many distinct ways are there to reach the top?

**Example**
```
Input: n = 5
Output: 8
```

## Approach

The last move to reach step `n` was either a single step from step `n-1`, or a double step from step `n-2`. That gives the recurrence:

```
ways(n) = ways(n-1) + ways(n-2)
```

with base cases `ways(1) = 1` and `ways(2) = 2`. This is exactly the Fibonacci recurrence in a different costume.

A plain recursive implementation of this recurrence recomputes the same sub-problems over and over (exponential blowup). Memoizing each result the first time it's computed — caching `ways(n)` in a map — turns it into linear work, since every distinct sub-problem from `1` to `n` only ever gets solved once.

## Complexity

- **Time:** `O(n)` — each of the `n` distinct sub-problems is computed exactly once thanks to memoization
- **Space:** `O(n)` — the memo map plus the recursion call stack

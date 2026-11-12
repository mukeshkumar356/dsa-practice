# Longest Consecutive Sequence

**Topic:** Arrays & Hashing
**Difficulty:** Medium

## Problem

Given an unsorted array of integers `nums`, return the length of the longest sequence of consecutive integers that can be formed from its elements. The algorithm must run in `O(n)` time.

**Example**
```
[100,4,200,1,3,2]  ->  4   (the sequence 1,2,3,4)
```

## Approach

1. Insert all numbers into a hash set.
2. A number `x` starts a sequence only if `x-1` is not in the set.
3. From each start, walk `x+1, x+2, ...` while present, tracking the length.

Each element is walked at most once across all starts, so the work is linear.

## Complexity

- **Time:** `O(n)`
- **Space:** `O(n)` — the hash set

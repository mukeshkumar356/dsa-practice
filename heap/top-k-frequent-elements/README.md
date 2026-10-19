# Top K Frequent Elements

**Topic:** Heap / Hashing
**Difficulty:** Medium

## Problem

Given an integer array `nums` and an integer `k`, return the `k` most frequent elements. The answer may be returned in any order.

**Example**
```
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
```

## Approach

A min-heap of size `k` over frequencies gives `O(n log k)`, but we can do better with **bucket sort**:

- Count each value's frequency with a hashmap.
- A frequency is at most `n`, so create `n + 1` buckets and put each value in `buckets[frequency]`.
- Scan the buckets from highest frequency to lowest, collecting values until `k` have been taken.

## Complexity

- **Time:** `O(n)` — counting, bucketing and scanning are each linear
- **Space:** `O(n)` — hashmap plus buckets

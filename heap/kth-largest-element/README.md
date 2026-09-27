# Kth Largest Element in an Array

**Topic:** Heap
**Difficulty:** Medium

## Problem

Given an integer array `nums` and an integer `k`, return the `k`th largest element — the `k`th largest in sorted order, not the `k`th distinct one.

**Example**
```
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
```

## Approach

Sorting the whole array works but costs `O(n log n)`. A smarter approach: maintain a **min-heap capped at size `k`**.

- Push every number onto the heap.
- Whenever the heap grows past `k` elements, pop the smallest — we only ever want to remember the `k` largest values seen so far.
- After scanning the whole array, the heap holds exactly the `k` largest elements, and the smallest among *those* (sitting at the root of the min-heap) is the answer.

The trick is using a **min**-heap (not a max-heap) capped at size `k` — it naturally keeps the top-k largest values while cheaply discarding everything else.

## Complexity

- **Time:** `O(n log k)` — each of the `n` numbers triggers at most one `O(log k)` heap operation
- **Space:** `O(k)` — the heap never holds more than `k` elements

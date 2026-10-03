# Subarray Sum Equals K

**Topic:** Prefix Sum
**Difficulty:** Medium

## Problem

Given an array of integers `nums` and an integer `k`, return the total number of contiguous subarrays whose sum equals `k`. The array may contain negative numbers.

**Example**
```
Input: nums = [1,1,1], k = 2
Output: 2   ([1,1] at index 0-1, and [1,1] at index 1-2)
```

## Approach

Negative numbers rule out a sliding window (the running sum isn't monotonic, so there's no reliable way to decide when to shrink the window). Prefix sums fix this.

Let `prefix[i]` be the sum of `nums[0..i-1]`. The sum of any subarray `nums[j..i-1]` is `prefix[i] - prefix[j]`. We want that to equal `k`, which rearranges to `prefix[j] = prefix[i] - k`.

So while scanning left to right and tracking a running prefix sum, at each step we check a hashmap for how many earlier prefix sums equal `current prefix - k` — each one marks a valid subarray ending at the current index — then record the current prefix sum for later indices to check against.

The map starts with `{0: 1}` so a subarray that starts at index 0 (no real "earlier" prefix) is still counted correctly, as if subtracting an empty prefix of 0.

## Complexity

- **Time:** `O(n)` — single pass, average O(1) hashmap operations
- **Space:** `O(n)` — prefix sum counts, worst case one entry per index

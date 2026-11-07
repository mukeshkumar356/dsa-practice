# Sort Colors

**Topic:** Sorting
**Difficulty:** Medium

## Problem

Given an array `nums` of `n` objects colored red, white, or blue (represented by `0`, `1`, `2`), sort them in-place so objects of the same color are adjacent, in the order red, white, blue. Do not use the library sort.

**Example**
```
nums = [2,0,2,1,1,0]  ->  [0,0,1,1,2,2]
nums = [2,0,1]        ->  [0,1,2]
```

## Approach

Dutch National Flag three-way partition with pointers `lo`, `i`, `hi`:

1. `nums[i] == 0`: swap with `lo`, advance `lo` and `i`.
2. `nums[i] == 2`: swap with `hi`, decrement `hi` (don't advance `i`; the swapped-in value is unchecked).
3. `nums[i] == 1`: advance `i`.

Stop when `i > hi`.

## Complexity

- **Time:** `O(n)` — one pass
- **Space:** `O(1)` — in-place

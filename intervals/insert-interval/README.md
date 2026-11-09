# Insert Interval

**Topic:** Intervals
**Difficulty:** Medium

## Problem

You are given a sorted list of non-overlapping intervals and a new interval. Insert the new interval so the list stays sorted and non-overlapping, merging overlaps if necessary.

**Example**
```
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
```

## Approach

Because the input is already sorted, one pass is enough:
1. Copy every interval that ends before the new interval starts.
2. Merge every interval whose start is `<=` the (growing) end of the new interval, widening it to the min start / max end.
3. Append the merged interval, then copy the rest.

## Complexity

- **Time:** `O(n)`
- **Space:** `O(n)` for the output

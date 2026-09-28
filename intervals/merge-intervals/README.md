# Merge Intervals

**Topic:** Intervals
**Difficulty:** Medium

## Problem

Given an array of intervals, merge all overlapping ones and return the non-overlapping intervals that cover all the input ranges.

**Example**
```
Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
```

## Approach

Sort the intervals by their start value first — this guarantees that if two intervals overlap, they'll be adjacent to each other once sorted, which makes a single linear pass enough to catch every overlap.

Then walk through the sorted list keeping a "current" interval:
- If the next interval's start is `<=` the current interval's end, they overlap — extend the current interval's end if the next one reaches further.
- Otherwise, there's a gap — the current interval is finished, and the next interval starts a new group.

## Complexity

- **Time:** `O(n log n)` — sorting dominates; the merge pass itself is `O(n)`
- **Space:** `O(n)` — for the sorted array and the result list

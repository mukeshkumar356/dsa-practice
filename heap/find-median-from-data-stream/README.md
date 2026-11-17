# Find Median from Data Stream

**Topic:** Heap / Design
**Difficulty:** Hard

## Problem

Design a data structure that supports `addNum(num)` to add an integer from a stream and `findMedian()` to return the median of all elements so far. For an even count the median is the mean of the two middle values.

**Example**
```
addNum(1); addNum(2); findMedian() -> 1.5
addNum(3); findMedian() -> 2.0
```

## Approach

Keep the data split into two halves using two heaps:

- `lo`: a max-heap with the smaller half.
- `hi`: a min-heap with the larger half.
- Invariant: `lo.size() == hi.size()` or `lo.size() == hi.size() + 1`.

To add a number, push it into `lo`, move `lo`'s max into `hi` (keeps every `lo` element <= every `hi` element), then move `hi`'s min back to `lo` if `hi` grew larger. The median is the top of `lo` for an odd count, or the mean of both tops for an even count.

## Complexity

- **Time:** `addNum` is `O(log n)`, `findMedian` is `O(1)`
- **Space:** `O(n)`

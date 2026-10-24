# Merge k Sorted Lists

**Topic:** Linked List (with a min-heap)
**Difficulty:** Hard

## Problem

You are given an array of `k` linked lists, each sorted in ascending order. Merge all of them into one sorted linked list and return it.

**Example**
```
Input:  lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]

Input:  lists = []
Output: []
```

## Approach

1. Push the head of every non-empty list into a min-heap ordered by node value.
2. Pop the smallest node, append it to the result (using a dummy head), and push its `next` node if it exists.
3. Repeat until the heap is empty. The heap never holds more than `k` nodes, so each push/pop is `O(log k)`.

## Complexity

- **Time:** `O(N log k)` where `N` is the total number of nodes
- **Space:** `O(k)` for the heap (nodes are relinked in place)

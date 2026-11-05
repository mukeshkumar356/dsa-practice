# Reorder List

**Topic:** Linked List
**Difficulty:** Medium

## Problem

Given the head of a singly linked list `L0 -> L1 -> ... -> Ln`, reorder it in place to `L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...`. Node values may not be modified; only the links.

**Example**
```
Input:  1 -> 2 -> 3 -> 4 -> 5
Output: 1 -> 5 -> 2 -> 4 -> 3
```

## Approach

1. Find the middle using slow/fast pointers.
2. Reverse the second half of the list (and cut the list in two).
3. Merge the halves by alternating one node from each.

## Complexity

- **Time:** `O(n)`
- **Space:** `O(1)`

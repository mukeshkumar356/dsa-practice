# Merge Two Sorted Lists

**Topic:** Linked List
**Difficulty:** Easy

## Problem

Given the heads of two sorted singly linked lists, merge them into a single sorted list by splicing together the nodes of the two lists, and return its head.

**Example**
```
Input:  1 -> 2 -> 4,  1 -> 3 -> 4
Output: 1 -> 1 -> 2 -> 3 -> 4 -> 4
```

## Approach

1. Create a dummy head and a `tail` pointer.
2. While both lists are non-empty, attach the smaller current node to `tail` and advance that list.
3. Attach whatever remains of the non-empty list.

## Complexity

- **Time:** `O(m + n)`
- **Space:** `O(1)`

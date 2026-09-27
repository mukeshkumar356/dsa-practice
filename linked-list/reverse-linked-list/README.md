# Reverse Linked List

**Topic:** Linked List
**Difficulty:** Easy

## Problem

Given the head of a singly linked list, reverse the list and return the new head.

**Example**
```
Input:  1 -> 2 -> 3 -> 4 -> 5
Output: 5 -> 4 -> 3 -> 2 -> 1
```

## Approach

Iterate through the list while keeping three references: `prev`, `current`, and a temporary `next`.

At each node:
1. Save `current.next` before it gets overwritten.
2. Point `current.next` backwards to `prev` (this is the actual reversal step).
3. Move `prev` and `current` one step forward.

When `current` becomes `null`, `prev` is sitting on the new head of the reversed list.

## Complexity

- **Time:** `O(n)` — every node is visited exactly once
- **Space:** `O(1)` — only a constant number of pointers are used, no extra list or recursion stack

# Linked List Cycle II

**Topic:** Linked List
**Difficulty:** Medium

## Problem

Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return `null`.

**Example**
```
Input:  3 -> 2 -> 0 -> -4 -> (back to 2)
Output: node with value 2
```

## Approach

Floyd's tortoise and hare:
1. Move `slow` one step and `fast` two steps. If `fast` reaches the end, there is no cycle.
2. If they meet, a cycle exists. Reset one pointer to `head`.
3. Advance both one step at a time; they meet at the cycle entry.

Why it works: let `a` be the distance from head to the entry, `b` the distance from the entry to the meeting point, and `L` the cycle length. Then `2(a+b) = a+b+kL`, so `a = kL - b`. Walking `a` steps from the meeting point therefore lands on the entry.

## Complexity

- **Time:** `O(n)`
- **Space:** `O(1)` — no hash set needed

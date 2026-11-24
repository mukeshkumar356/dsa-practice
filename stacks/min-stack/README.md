# Min Stack

**Topic:** Stacks
**Difficulty:** Medium

## Problem

Design a stack that supports `push`, `pop`, `top`, and retrieving the minimum element, each in constant time.

**Example**
```
push(-2), push(0), push(-3)
getMin() -> -3
pop()
top()    -> 0
getMin() -> -2
```

## Approach

Maintain two stacks: the main stack of values and a second stack of running minimums.
1. On `push(v)`, push `v` on the main stack; if `v <= current min`, push it on the min stack too.
2. On `pop()`, pop the main stack; if the value equals the min stack's top, pop the min stack.
3. `getMin()` is the top of the min stack.

Using `<=` ensures duplicate minimums are tracked and one pop doesn't lose the minimum.

## Complexity

- **Time:** `O(1)` per operation
- **Space:** `O(n)` — the two stacks

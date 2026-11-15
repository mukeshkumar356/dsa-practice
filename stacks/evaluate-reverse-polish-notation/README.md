# Evaluate Reverse Polish Notation

**Topic:** Stacks
**Difficulty:** Medium

## Problem

Evaluate an arithmetic expression given in Reverse Polish (postfix) notation as an array of string tokens. Operators are `+`, `-`, `*`, `/`; division truncates toward zero.

**Example**
```
Input:  ["4", "13", "5", "/", "+"]
Output: 6      // 4 + (13 / 5)
```

## Approach

Use a stack of operands.
1. If the token is a number, push it.
2. If it is an operator, pop the right operand `b`, then the left operand `a`, compute `a op b`, and push the result.
3. After all tokens, the stack holds the final value.

Order matters for `-` and `/`: the first pop is the right operand.

## Complexity

- **Time:** `O(n)` — one pass over the tokens
- **Space:** `O(n)` — the operand stack

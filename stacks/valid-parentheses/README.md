# Valid Parentheses

**Topic:** Stacks
**Difficulty:** Easy

## Problem

Given a string containing only `(`, `)`, `{`, `}`, `[`, `]`, determine if the brackets are properly opened and closed in the right order.

**Example**
```
Input: "([{}])"  -> true
Input: "(]"       -> false
Input: "(("       -> false
```

## Approach

This is a classic stack problem. Every opening bracket needs to be "remembered" until its matching closing bracket shows up, and it must be the *most recent* unmatched opening bracket — which is exactly what a stack (LIFO) gives us for free.

- On an opening bracket, push it.
- On a closing bracket, pop the stack and confirm it matches. If the stack is empty (nothing to match against) or the popped bracket is the wrong type, the string is invalid.
- At the end, the stack must be empty — otherwise some opening brackets were never closed.

## Complexity

- **Time:** `O(n)` — one pass through the string
- **Space:** `O(n)` — worst case (e.g. `"((((("`) pushes every character

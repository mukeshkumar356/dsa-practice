# Longest Repeating Character Replacement

**Topic:** Sliding Window
**Difficulty:** Medium

## Problem

Given a string `s` of uppercase English letters and an integer `k`, you may change at most `k` characters to any other uppercase letter. Return the length of the longest substring containing the same letter after performing those changes.

**Example**
```
Input: s = "AABABBA", k = 1
Output: 4   (change one 'B' to 'A' -> "AAAA" ... or "BBBB" from "ABBB")
```

## Approach

Maintain a window with a count array of its letters. The window is valid when `length - maxFreq <= k`, since every character that is not the most frequent letter can be replaced. `maxFreq` is only ever increased (a stale, too-large value can never produce a wrong larger answer, because the window only grows when a genuinely higher frequency is found). When the window turns invalid, slide it by moving `left` one step instead of shrinking, so its size is monotonically non-decreasing and the final size is the answer.

## Complexity

- **Time:** `O(n)` — single pass
- **Space:** `O(1)` — 26-element count array

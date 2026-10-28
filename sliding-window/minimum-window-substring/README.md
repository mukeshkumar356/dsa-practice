# Minimum Window Substring

**Topic:** Sliding Window
**Difficulty:** Hard

## Problem

Given two strings `s` and `t`, return the smallest substring of `s` such that every character in `t` (including duplicates) is in the window. If there is no such substring, return `""`.

**Example**
```
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
```

## Approach

Keep a count array `need` for the characters of `t` and a counter `missing` of required characters not yet covered. Move `right` forward, decrementing `need` for each character; a character that was still needed reduces `missing`. Once `missing == 0` the window is valid: advance `left` while the left character is surplus (its count is negative), record the window if it is the shortest so far, then drop one required character and keep expanding.

## Complexity

- **Time:** `O(|s| + |t|)` — each pointer only moves forward
- **Space:** `O(1)` — fixed-size count array

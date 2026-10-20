# Longest Palindromic Substring

**Topic:** Strings
**Difficulty:** Medium

## Problem

Given a string `s`, return the longest palindromic substring in `s`.

**Example**
```
Input: s = "babad"
Output: "bab"   ("aba" is also valid)
```

## Approach

**Expand around center.** Every palindrome is symmetric about a center, which is either a character (odd length) or the gap between two characters (even length). For each of the `2n - 1` centers, expand outward while the two ends match, and keep the widest window.

## Complexity

- **Time:** `O(n^2)` — `2n - 1` centers, each expanding up to `n`
- **Space:** `O(1)` — only indices are tracked

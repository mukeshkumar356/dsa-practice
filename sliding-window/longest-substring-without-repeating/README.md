# Longest Substring Without Repeating Characters

**Topic:** Sliding Window
**Difficulty:** Medium

## Problem

Given a string `s`, find the length of the longest substring without any repeating characters.

**Example**
```
Input: "abcabcbb"
Output: 3   ("abc")
```

## Approach

Maintain a window `[left, right]` that always contains only unique characters, and a hashmap of each character's last-seen index.

- Expand the window by moving `right` forward one character at a time.
- If the character at `right` has been seen before, **and** that previous occurrence is still inside the current window (`lastSeenAt.get(c) >= left`), snap `left` forward to just past that duplicate. This removes the duplicate from the window in one jump instead of shrinking it character by character.
- Track the best (longest) window size seen so far.

The key detail is the `>= left` check — without it, a stale index from *before* the current window would incorrectly shrink the window even though that old occurrence isn't actually inside it anymore.

## Complexity

- **Time:** `O(n)` — `right` visits every character once, and `left` only ever moves forward, never backward
- **Space:** `O(min(n, k))` where `k` is the size of the character set — the hashmap holds at most one entry per distinct character

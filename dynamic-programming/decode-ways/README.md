# Decode Ways

**Topic:** Dynamic Programming
**Difficulty:** Medium

## Problem

A message of digits is encoded with `A=1, B=2, ..., Z=26`. Given a digit string `s`, return the number of ways to decode it. Strings with invalid leading zeros (e.g. `"06"`) cannot be decoded.

**Example**
```
s = "226"  ->  3   ("BZ", "VF", "BBF")
```

## Approach

`dp[i]` = number of ways to decode the first `i` characters. Add `dp[i-1]` if the last digit is `1-9`, and add `dp[i-2]` if the last two digits form a number in `10..26`. Only two previous values are kept.

## Complexity

- **Time:** `O(n)`
- **Space:** `O(1)`

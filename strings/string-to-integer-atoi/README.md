# String to Integer (atoi)

**Topic:** Strings
**Difficulty:** Medium

## Problem

Implement `myAtoi(String s)`: skip leading spaces, read an optional `+`/`-` sign, read digits until a non-digit is hit, and return the value clamped to the 32-bit signed integer range `[-2^31, 2^31 - 1]`. Return 0 if no digits are read.

**Example**
```
Input: s = "   -042"
Output: -42
```

## Approach

One left-to-right pass: skip whitespace, read the sign, then accumulate digits. Overflow is detected *before* it happens by checking whether `result > MAX/10` or `result == MAX/10 && digit > MAX%10`, and clamping to `MAX_VALUE` / `MIN_VALUE` based on the sign.

## Complexity

- **Time:** `O(n)`
- **Space:** `O(1)`

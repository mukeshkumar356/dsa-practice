# Longest Common Subsequence

**Topic:** Dynamic Programming
**Difficulty:** Medium

## Problem

Given two strings `text1` and `text2`, return the length of their longest common subsequence. A subsequence keeps the relative order of characters but doesn't need to be contiguous.

**Example**
```
Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: "ace" is a subsequence of both strings.
```

## Approach

Define `dp[i][j]` as the LCS length between the first `i` characters of `text1` and the first `j` characters of `text2`.

- **Characters match** (`text1[i-1] == text2[j-1]`): this character extends the LCS we already found for the strings *without* it, so `dp[i][j] = dp[i-1][j-1] + 1`.
- **Characters don't match**: the LCS can't use both characters, so we take the better of dropping one from either string: `dp[i][j] = max(dp[i-1][j], dp[i][j-1])`.

Base case: `dp[0][j] = dp[i][0] = 0` (an empty string has no common subsequence with anything).

The answer sits in `dp[m][n]`, the bottom-right corner of the table.

## Complexity

- **Time:** `O(m * n)` — one value computed per cell in an `(m+1) x (n+1)` table
- **Space:** `O(m * n)` — the DP table (can be optimized to `O(min(m, n))` by keeping only two rows, since each row only depends on the previous one)

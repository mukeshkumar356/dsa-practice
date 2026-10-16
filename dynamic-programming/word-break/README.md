# Word Break

**Topic:** Dynamic Programming (prefix DP)
**Difficulty:** Medium

## Problem

Given a string `s` and a list of words `wordDict`, return `true` if `s` can be split into a sequence of one or more dictionary words. Words may be reused.

**Example**
```
Input:  s = "leetcode", wordDict = ["leet","code"]
Output: true

Input:  s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
```

## Approach

`dp[i]` means the prefix `s[0..i)` can be segmented.

1. `dp[0] = true` (empty prefix).
2. For each `i`, try split points `j < i`: if `dp[j]` is true and `s[j..i)` is in the dictionary, then `dp[i] = true`.
3. Limit `i - j` to the longest dictionary word to prune work.
4. The answer is `dp[n]`.

## Complexity

- **Time:** `O(n * L^2)` where `L` is the longest word length
- **Space:** `O(n)` plus the dictionary set

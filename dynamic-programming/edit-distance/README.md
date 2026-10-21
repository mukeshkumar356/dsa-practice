# Edit Distance

**Topic:** Dynamic Programming (2D DP on two strings)
**Difficulty:** Hard

## Problem

Given two strings `word1` and `word2`, return the minimum number of operations to convert `word1` into `word2`. Allowed operations: insert a character, delete a character, replace a character.

**Example**
```
Input:  word1 = "horse", word2 = "ros"
Output: 3   (horse -> rorse -> rose -> ros)

Input:  word1 = "intention", word2 = "execution"
Output: 5
```

## Approach

`dp[i][j]` = edit distance between `word1[0..i)` and `word2[0..j)`.

1. Base cases: `dp[i][0] = i`, `dp[0][j] = j`.
2. If `word1[i-1] == word2[j-1]`, `dp[i][j] = dp[i-1][j-1]`.
3. Otherwise `dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])` (delete, insert, replace).
4. Each row depends only on the previous one, so two rolling rows suffice.

## Complexity

- **Time:** `O(m * n)`
- **Space:** `O(n)`

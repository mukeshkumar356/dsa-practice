# Group Anagrams

**Topic:** Arrays & Hashing
**Difficulty:** Medium

## Problem

Given an array of strings `strs`, group the anagrams together. You may return the answer in any order.

**Example**
```
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
```

## Approach

Two words are anagrams exactly when they have identical letter counts. For each word, build a canonical key from its 26 letter counts (separated by `#`) and use it in a hashmap from key to the list of words sharing it. The map's values are the answer.

Using counts instead of sorting each word's characters removes an extra `log k` factor.

## Complexity

- **Time:** `O(n * k)` — `n` words of length at most `k`
- **Space:** `O(n * k)` — the map holds every word and its key

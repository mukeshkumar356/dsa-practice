# Word Ladder

**Topic:** Graphs
**Difficulty:** Hard

## Problem

Given `beginWord`, `endWord` and a dictionary `wordList`, return the number of words in the shortest transformation sequence from `beginWord` to `endWord`, where each step changes exactly one letter and every intermediate word must be in `wordList`. Return `0` if no sequence exists.

**Example**
```
beginWord = "hit", endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]
-> 5   (hit -> hot -> dot -> dog -> cog)
```

## Approach

Model words as nodes of an unweighted graph, with an edge between words differing by one letter. BFS from `beginWord` yields the shortest path.
1. Put the word list in a hash set; if `endWord` is missing, return `0`.
2. BFS level by level; for each word try every letter `a-z` at every position.
3. If the new word is in the set, enqueue it and remove it from the set (marks it visited).
4. The level count when `endWord` is dequeued is the answer.

## Complexity

- **Time:** `O(N * L^2 * 26)` — `N` words of length `L`
- **Space:** `O(N * L)` — word set and queue

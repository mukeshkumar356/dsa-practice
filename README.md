# DSA Practice

Data Structures & Algorithms problems, solved in Java, organized by topic. Each problem folder includes a working, tested solution plus a short write-up covering the approach and time/space complexity.

The goal is to keep this genuinely useful as a reference — not just for me, but for anyone else preparing for the same kind of problems.

## Problems Solved: 17

### Arrays & Hashing
- [Two Sum](arrays-hashing/two-sum) — hashmap, O(n)

### Stacks
- [Valid Parentheses](stacks/valid-parentheses) — stack matching, O(n)

### Linked List
- [Reverse Linked List](linked-list/reverse-linked-list) — iterative pointer reversal, O(n)

### Trees
- [Binary Tree Level Order Traversal](trees/binary-tree-level-order-traversal) — BFS, O(n)

### Dynamic Programming
- [Longest Common Subsequence](dynamic-programming/longest-common-subsequence) — 2D DP table, O(m*n)

### Graphs
- [Number of Islands](graphs/number-of-islands) — DFS flood fill, O(rows*cols)

### Binary Search
- [Search in Rotated Sorted Array](binary-search/search-rotated-sorted-array) — modified binary search, O(log n)

### Sliding Window
- [Longest Substring Without Repeating Characters](sliding-window/longest-substring-without-repeating) — sliding window + hashmap, O(n)

### Greedy
- [Jump Game](greedy/jump-game) — greedy reachability, O(n)

### Backtracking
- [Subsets](backtracking/subsets) — include/exclude backtracking, O(n * 2^n)

### Heap
- [Kth Largest Element in an Array](heap/kth-largest-element) — min-heap capped at size k, O(n log k)

### Two Pointers
- [Container With Most Water](two-pointers/container-with-most-water) — two pointers from both ends, O(n)

### Intervals
- [Merge Intervals](intervals/merge-intervals) — sort + linear merge, O(n log n)

### Bit Manipulation
- [Single Number](bit-manipulation/single-number) — XOR trick, O(n)

### Recursion
- [Climbing Stairs](recursion/climbing-stairs) — Fibonacci recurrence with memoization, O(n)

### Union-Find
- [Number of Provinces](union-find/number-of-provinces) — disjoint set union, O(n^2 * alpha(n))

### Tries
- [Implement Trie (Prefix Tree)](tries/implement-trie) — 26-ary tree with an isEnd flag, O(L) per op

## How each problem is organized

```
topic-name/
  problem-name/
    Solution.java   <- the actual solution, with a runnable main() for quick verification
    README.md       <- problem statement, approach explanation, complexity analysis
```

More problems get added over time, across more topics (math, design, prefix-sum, etc.) as I keep practicing.

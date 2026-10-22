# DSA Practice

Data Structures & Algorithms problems, solved in Java, organized by topic. Each problem folder includes a working, tested solution plus a short write-up covering the approach and time/space complexity.

The goal is to keep this genuinely useful as a reference — not just for me, but for anyone else preparing for the same kind of problems.

## Problems Solved: 33

### Arrays & Hashing
- [Two Sum](arrays-hashing/two-sum) — hashmap, O(n)
- [Group Anagrams](arrays-hashing/group-anagrams) — letter-count key + hashmap, O(n*k) — Medium, solved 2026-10-11

### Strings
- [Longest Palindromic Substring](strings/longest-palindromic-substring) — expand around center, O(n^2) time, O(1) space — Medium, solved 2026-10-20

### Stacks
- [Valid Parentheses](stacks/valid-parentheses) — stack matching, O(n)
- [Daily Temperatures](stacks/daily-temperatures) — monotonic stack of indices, O(n) — Medium, solved 2026-10-12

### Linked List
- [Reverse Linked List](linked-list/reverse-linked-list) — iterative pointer reversal, O(n)
- [Linked List Cycle II](linked-list/linked-list-cycle-ii) — Floyd's tortoise & hare, O(n) time, O(1) space — Medium, solved 2026-10-12

### Trees
- [Binary Tree Level Order Traversal](trees/binary-tree-level-order-traversal) — BFS, O(n)
- [Validate Binary Search Tree](trees/validate-binary-search-tree) — DFS with (lo, hi) bounds, O(n) — Medium, solved 2026-10-10
- [Binary Tree Maximum Path Sum](trees/binary-tree-maximum-path-sum) — post-order DFS with downward gains, O(n) — Hard, solved 2026-10-17

### Dynamic Programming
- [Longest Common Subsequence](dynamic-programming/longest-common-subsequence) — 2D DP table, O(m*n)
- [Coin Change](dynamic-programming/coin-change) — bottom-up DP (unbounded knapsack), O(amount * n) — Medium, solved 2026-10-09
- [Word Break](dynamic-programming/word-break) — prefix DP + hash set, O(n * L^2) — Medium, solved 2026-10-16
- [Edit Distance](dynamic-programming/edit-distance) — 2D DP with rolling rows, O(m*n) time, O(n) space — Hard, solved 2026-10-21

### Graphs
- [Number of Islands](graphs/number-of-islands) — DFS flood fill, O(rows*cols)
- [Course Schedule](graphs/course-schedule) — Kahn's topological sort (cycle detection), O(V+E)
- [Rotting Oranges](graphs/rotting-oranges) — multi-source BFS, O(rows*cols) — Medium, solved 2026-10-15

### Binary Search
- [Search in Rotated Sorted Array](binary-search/search-rotated-sorted-array) — modified binary search, O(log n)
- [Median of Two Sorted Arrays](binary-search/median-of-two-sorted-arrays) — binary search on partition of smaller array, O(log(min(m,n))) — Hard, solved 2026-10-22

### Sliding Window
- [Longest Substring Without Repeating Characters](sliding-window/longest-substring-without-repeating) — sliding window + hashmap, O(n)

### Greedy
- [Jump Game](greedy/jump-game) — greedy reachability, O(n)

### Backtracking
- [Subsets](backtracking/subsets) — include/exclude backtracking, O(n * 2^n)
- [Permutations](backtracking/permutations) — backtracking with a used array, O(n * n!) — Medium, solved 2026-10-18

### Heap
- [Kth Largest Element in an Array](heap/kth-largest-element) — min-heap capped at size k, O(n log k)
- [Top K Frequent Elements](heap/top-k-frequent-elements) — hashmap + bucket sort by frequency, O(n) — Medium, solved 2026-10-19

### Two Pointers
- [Container With Most Water](two-pointers/container-with-most-water) — two pointers from both ends, O(n)
- [Trapping Rain Water](two-pointers/trapping-rain-water) — two pointers with running left/right max, O(n) time, O(1) space — Hard, solved 2026-10-13

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

### Prefix Sum
- [Subarray Sum Equals K](prefix-sum/subarray-sum-equals-k) — prefix sum + hashmap, O(n)

## How each problem is organized

```
topic-name/
  problem-name/
    Solution.java   <- the actual solution, with a runnable main() for quick verification
    README.md       <- problem statement, approach explanation, complexity analysis
```

More problems get added over time, across more topics (math, design, etc.) as I keep practicing.

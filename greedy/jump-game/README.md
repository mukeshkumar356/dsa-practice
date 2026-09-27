# Jump Game

**Topic:** Greedy
**Difficulty:** Medium

## Problem

Given an array `nums` where `nums[i]` is the maximum jump length from index `i`, determine if you can reach the last index starting from index 0.

**Example**
```
Input: [2, 3, 1, 1, 4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
```

## Approach

No need to try every possible jump combination. Greedily track the furthest index reachable so far (`reach`) while scanning left to right:

- If the current index `i` is already beyond `reach`, there's no way anything before it could have jumped this far — it's unreachable, so return `false`.
- Otherwise, update `reach = max(reach, i + nums[i])` — this position might extend how far we can go.
- If `reach` ever covers the last index, we can stop early and return `true`.

The insight is that we don't care *which* path gets us there, only whether the furthest-reachable frontier ever stalls before the end.

## Complexity

- **Time:** `O(n)` — one pass through the array
- **Space:** `O(1)` — a single variable tracks the frontier

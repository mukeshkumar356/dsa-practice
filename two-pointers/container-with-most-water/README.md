# Container With Most Water

**Topic:** Two Pointers
**Difficulty:** Medium

## Problem

Given `n` non-negative integers representing line heights at each index, find two lines that form a container (with the x-axis) holding the most water.

**Example**
```
Input: [1,8,6,2,5,4,8,3,7]
Output: 49
```

## Approach

Start with pointers at both ends — the widest possible container. The water held is `width * min(height[left], height[right])`, since water spills over the shorter wall.

The key insight: moving the **taller** pointer inward can never help. Width shrinks either way, and the limiting height is still capped by the shorter wall (or gets worse if the taller one moves and becomes the new limiting wall). So the only move worth making is shrinking the **shorter** wall's side — that's the only pointer move with any chance of finding a taller line and a bigger area.

Repeat until the pointers meet, tracking the best area seen.

## Complexity

- **Time:** `O(n)` — the two pointers together traverse the array once
- **Space:** `O(1)` — just two index variables

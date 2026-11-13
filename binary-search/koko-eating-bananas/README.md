# Koko Eating Bananas

**Topic:** Binary Search
**Difficulty:** Medium

## Problem

Given piles of bananas and `h` hours, Koko eats at speed `k` bananas/hour, taking one pile per hour (leftovers in a pile are wasted for that hour). Return the minimum integer `k` so she finishes all piles within `h` hours.

**Example**
```
piles = [3,6,7,11], h = 8  ->  4
```

## Approach

Binary search on the answer `k` in `[1, max(piles)]`. Hours needed = `sum(ceil(pile/k))`, which only decreases as `k` grows, so the check is monotonic. If `k` is feasible, search lower; otherwise search higher.

## Complexity

- **Time:** `O(n log(max(piles)))`
- **Space:** `O(1)`

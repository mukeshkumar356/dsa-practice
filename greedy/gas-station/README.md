# Gas Station

**Topic:** Greedy
**Difficulty:** Medium

## Problem

There are `n` gas stations along a circular route. `gas[i]` is the fuel at station `i`, and `cost[i]` is the fuel needed to drive from station `i` to `i + 1`. Starting with an empty tank, return the index of the station from which you can complete the full circuit, or `-1` if it's impossible. If a solution exists it is unique.

**Example**
```
Input: gas = [1, 2, 3, 4, 5], cost = [3, 4, 5, 1, 2]
Output: 3
Explanation: Start at station 3 (fuel 4), reach station 4 with 5, then 0, 1, 2 and back to 3.
```

## Approach

- If the total gas is less than the total cost, no starting point can work.
- Otherwise scan once, keeping the running tank from the current candidate start. If the tank drops below zero at station `i`, then none of the stations between the candidate and `i` can be the start either (each reached `i` with a non-negative tank, so they would fail no later than the candidate). Move the candidate to `i + 1` and reset the tank.
- Because the total is non-negative, the final candidate is guaranteed to complete the loop.

## Complexity

- **Time:** `O(n)` — one pass
- **Space:** `O(1)` — a few counters

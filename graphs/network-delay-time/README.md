# Network Delay Time

**Topic:** Graphs (Dijkstra's shortest path)
**Difficulty:** Medium

## Problem

There are `n` nodes labeled `1..n` and directed weighted edges `times[i] = (u, v, w)`, meaning a signal travels from `u` to `v` in `w` time. A signal is sent from node `k`. Return the minimum time for all nodes to receive it, or `-1` if that is impossible.

**Example**
```
Input:  times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2

Input:  times = [[1,2,1]], n = 2, k = 2
Output: -1   (node 1 is unreachable)
```

## Approach

All nodes have the signal once the farthest node is reached, so the answer is the maximum shortest-path distance from `k`. Edge weights are non-negative, so Dijkstra applies:

1. Build an adjacency list and set every distance to infinity except `dist[k] = 0`.
2. Use a min-heap of `(distance, node)`. Pop the closest entry and skip it if it is stale (`d > dist[u]`).
3. Relax each outgoing edge, pushing an improved neighbor onto the heap.
4. If any node is still at infinity return `-1`; otherwise return the largest distance.

## Complexity

- **Time:** `O(E log V)`
- **Space:** `O(V + E)`

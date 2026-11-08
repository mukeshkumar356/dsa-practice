# Clone Graph

**Topic:** Graphs (BFS + hash map)
**Difficulty:** Medium

## Problem

Given a reference to a node in a connected undirected graph, return a deep copy of the graph. Each node contains an integer value and a list of its neighbors.

**Example**
```
Input:  adjacency list [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]   (same shape, entirely new node objects)
```

## Approach

The challenge is handling cycles and making sure each node is cloned exactly once:

1. Keep a map from original node to its clone; it also acts as the visited set.
2. Clone the start node and enqueue it.
3. For each dequeued node, look at each neighbor: if it has no clone yet, create one and enqueue it.
4. Append the neighbor's clone to the current node's clone's neighbor list.

## Complexity

- **Time:** `O(V + E)` — each node and edge is processed once
- **Space:** `O(V)` — the map and the queue

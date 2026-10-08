# Course Schedule

**Topic:** Graphs (Topological Sort)
**Difficulty:** Medium

## Problem

There are `numCourses` courses labeled `0` to `numCourses - 1`. `prerequisites[i] = [a, b]` means you must take course `b` before course `a`. Return `true` if you can finish all courses, otherwise `false`.

**Example**
```
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false   (each course requires the other - a cycle)
```

## Approach

Finishing all courses is possible exactly when the prerequisite graph is a DAG (no cycle). Use Kahn's algorithm:

1. Add an edge `b -> a` for every prerequisite and record each course's in-degree.
2. Queue every course with in-degree 0 (no unmet prerequisites).
3. Pop a course, count it as taken, and decrement the in-degree of its dependents, enqueueing any that reach 0.
4. If all courses were taken, there is no cycle; otherwise the remaining courses are blocked by a cycle.

## Complexity

- **Time:** `O(V + E)` — each course and prerequisite is processed once
- **Space:** `O(V + E)` — adjacency list, in-degree array and queue

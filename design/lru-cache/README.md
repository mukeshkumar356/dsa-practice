# LRU Cache

**Topic:** Design
**Difficulty:** Medium

## Problem

Design a Least Recently Used (LRU) cache with a fixed capacity. `get(key)` returns the value or `-1` if the key is absent. `put(key, value)` inserts or updates the key, and if the capacity is exceeded it evicts the least recently used key. Both operations must run in `O(1)` average time.

**Example**
```
LRUCache cache = new LRUCache(2);
cache.put(1, 1); cache.put(2, 2);
cache.get(1);    // 1
cache.put(3, 3); // evicts key 2
cache.get(2);    // -1
```

## Approach

- A hash map gives `O(1)` lookup from key to list node.
- A doubly linked list keeps entries ordered by recency: the node after the head sentinel is the most recent, the node before the tail sentinel is the least recent.
- Every `get`/`put` unlinks the touched node and re-inserts it at the front, both `O(1)` thanks to the `prev`/`next` pointers.
- When inserting into a full cache, evict `tail.prev` and remove its key from the map.
- Sentinel head/tail nodes avoid null checks at the list ends.

## Complexity

- **Time:** `O(1)` per `get` / `put`
- **Space:** `O(capacity)` — map entries and list nodes

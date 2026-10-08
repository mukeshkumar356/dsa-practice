import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * There are numCourses courses labeled 0..numCourses-1. prerequisites[i] =
 * [a, b] means you must take course b before course a. Return true if it is
 * possible to finish all courses (i.e. the prerequisite graph has no cycle).
 *
 * Approach: Kahn's algorithm (BFS topological sort). Build a directed graph
 * with an edge b -> a for each prerequisite and count each course's
 * in-degree. Start with every course that has no prerequisites, and
 * repeatedly "take" a course: decrement the in-degree of its dependents and
 * enqueue any that reach 0. If we manage to take all numCourses courses
 * there is no cycle; otherwise the leftover courses are stuck in a cycle.
 *
 * Time complexity:  O(V + E) - each course and prerequisite is processed once
 * Space complexity: O(V + E) - adjacency list, in-degree array and queue
 */
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]);
            inDegree[p[0]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int taken = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            taken++;
            for (int next : graph.get(course)) {
                if (--inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return taken == numCourses;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(s.canFinish(2, new int[][]{{1, 0}}));                  // true
        System.out.println(s.canFinish(2, new int[][]{{1, 0}, {0, 1}}));          // false (cycle)
        System.out.println(s.canFinish(1, new int[][]{}));                        // true  (no prerequisites)
        System.out.println(s.canFinish(4, new int[][]{{1, 0}, {2, 1}, {3, 2}, {1, 3}})); // false (cycle 1->2->3->1)
        System.out.println(s.canFinish(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})); // true  (diamond)
    }
}

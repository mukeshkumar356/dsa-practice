/**
 * There are n gas stations on a circular route. gas[i] is the fuel available
 * at station i and cost[i] is the fuel needed to travel from station i to
 * i+1. Return the index of the station from which you can complete the full
 * circuit (starting with an empty tank), or -1 if impossible. The answer,
 * if it exists, is unique.
 *
 * Approach: greedy, single pass. If total gas < total cost, no start works.
 * Otherwise, scan while tracking the running tank. When the tank goes
 * negative at station i, no station from the current start up to i can be a
 * valid start (each reached i with a non-negative tank, so they'd fail even
 * earlier-or-equal), so reset the candidate start to i+1 and the tank to 0.
 * Since total >= 0, the final candidate is guaranteed to work.
 *
 * Time complexity:  O(n) - single pass
 * Space complexity: O(1) - a few counters
 */
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0; // net fuel over the whole circuit
        int tank = 0;  // net fuel since the current candidate start
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];
            total += diff;
            tank += diff;
            if (tank < 0) {
                start = i + 1; // can't reach i+1 from any earlier candidate
                tank = 0;
            }
        }

        return total >= 0 ? start : -1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.canCompleteCircuit(new int[] { 1, 2, 3, 4, 5 }, new int[] { 3, 4, 5, 1, 2 })); // 3
        System.out.println(s.canCompleteCircuit(new int[] { 2, 3, 4 }, new int[] { 3, 4, 3 }));             // -1
        System.out.println(s.canCompleteCircuit(new int[] { 5 }, new int[] { 4 }));                         // 0 (single station)
        System.out.println(s.canCompleteCircuit(new int[] { 5, 1, 2, 3, 4 }, new int[] { 4, 4, 1, 5, 1 })); // 4
    }
}

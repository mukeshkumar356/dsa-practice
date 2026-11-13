/**
 * Koko has piles of bananas and h hours. Each hour she picks one pile and eats
 * up to k bananas from it (finishing early wastes the rest of that hour).
 * Return the minimum integer eating speed k that lets her finish within h hours.
 *
 * Approach: binary search on the answer. Hours needed is non-increasing in k,
 * so the feasibility predicate is monotonic. Search k in [1, max(piles)];
 * for a candidate k, hours = sum(ceil(pile / k)). If hours <= h, k works and
 * we try smaller; otherwise go larger.
 *
 * Time complexity:  O(n * log(max(piles)))
 * Space complexity: O(1)
 */
public class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int lo = 1, hi = 0;
        for (int p : piles) hi = Math.max(hi, p);

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            long hours = 0;
            for (int p : piles) hours += (p + mid - 1) / mid; // ceil(p / mid)
            if (hours <= h) hi = mid;   // feasible, try slower
            else lo = mid + 1;          // too slow
        }
        return lo;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] piles = {{3, 6, 7, 11}, {30, 11, 23, 4, 20}, {30, 11, 23, 4, 20}, {5}, {1000000000, 1000000000}};
        int[] hs =       {8, 5, 6, 5, 3};
        int[] expected = {4, 30, 23, 1, 1000000000};
        boolean ok = true;
        for (int i = 0; i < piles.length; i++) {
            int got = s.minEatingSpeed(piles[i], hs[i]);
            System.out.println("test " + i + ": got " + got + ", expected " + expected[i]
                    + (got == expected[i] ? " OK" : " FAIL"));
            ok &= got == expected[i];
        }
        System.out.println(ok ? "ALL PASSED" : "SOME FAILED");
    }
}

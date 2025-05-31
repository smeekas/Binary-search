import java.util.Arrays;
import java.util.Collections;

class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        /**
         * for every index in height,
         * we can check sort height differences till {index}
         * then try to use ladders and bricks
         * first we can use all ladders for big differences
         * then use bricks and check if we can cover entire array (obviously till
         * {index})
         * 
         * if we check one by one 
         * O(n*(n*logN+n))
         * 
         * with binary search
         * O(logN*(n*logN+n))
         */
        int l = 0, r = heights.length - 1;
        int ans = 0;
        while (l <= r) {
            int midIndex = l + (r - l) / 2;
            int len = check(midIndex, heights, bricks, ladders);
            if (len == midIndex) {
                // we can cover entire array
                // can we do better?
                // increase the midIndex, but store midIndex as potential answer
                l = midIndex + 1;
                ans = midIndex;
            } else if (len < midIndex) {
                // we are not able to cover till midIndex with available ladder and bricks
                // decrease the index
                r = midIndex - 1;
            } else {
                // we are able to cover till midIndex with available ladder and bricks
                // can we do better?
                // increase the midIndex, but store midIndex as potential answer
                l = midIndex + 1;
                ans = midIndex;
            }
        }
        return ans;
    }

    int check(int limit, int h[], int bricks, int ladders) {
        // this function returns how much we can complete the array
        Integer diff[] = new Integer[limit];
        for (int i = 1; i <= limit; i++) {
            diff[i - 1] = h[i] - h[i - 1];
        }
        Arrays.sort(diff, Collections.reverseOrder());
        if (ladders > diff.length)
            // if ladder can cover entire array then no need to check further
            return limit;
        int newIndex = ladders;
        // we can start from {ladders} index as ladder can cover that many indexes
        for (int i = newIndex; i < diff.length; i++) {
            int difference = diff[i];
            if (difference > 0) {
                // if positive difference, then we may have to use bricks
                if (difference <= bricks) {
                    // difference is less then available bricks
                    bricks -= difference; // use the bricks
                    continue;
                }
                // bricks cannot cover difference anymore
                return i;
            }
        }
        // we are successfully out of loop means we covered entire array till {limit}
        return limit;
    }
}
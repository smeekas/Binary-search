import java.util.Arrays;

class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        // bruteforce is two loop=> all possible pair, collect all pair in heap
        // better

        // since, we take distance as absolute difference, position of i & j do not
        // matter
        // so we can sort the array
        Arrays.sort(nums);
        int n = nums.length;
        int minDiff = 0, maxDiff = nums[n - 1] - nums[0];
        // min Diff can be 0 (diff with same number)
        // max Diff can be last ele - first ele
        // we can do trial and error on this search space!
        int l = minDiff, r = maxDiff;
        int ans = -1;
        while (l <= r) {
            int newDiff = l + (r - l) / 2;
            int pairCount = getCount(nums, newDiff);
            if (pairCount == k) {
                // our current pair have K smaller pair on left
                // current pair is Kth small
                // possible answer
                // we can search on left
                // may be we can have duplicates.
                // (this is standard template)
                ans = newDiff;
                r = newDiff - 1;
            } else if (pairCount < k) {
                // we do not have enough pair on K
                // current pair is < Kth small
                // search on right
                l = newDiff + 1;
            } else {
                // pairCount > k
                // we have more than K element on left
                // this pair is possible answer since we have at-least K small pair on left side
                ans = newDiff;
                r = newDiff - 1;
            }
        }
        return ans;
    }

    int getCount(int[] arr, int diff) {
        // how to get count?
        // two loops?
        // that would be bruteforce
        // what about sliding window?

        /*
         * array length is atleast 2
         * we want pair with min diff.
         * we can have two pointer that start at 0th and 1st index respectively.
         * for index-l and index-r, if r-l diff is less then {diff}, means arr[l+1] -
         * arr[r], arr[l+2] - arr[r] .... arr[r-2] - arr[r], arr[r-1] - r all will have
         * lesser difference
         * 
         * in short all number from l to r-1 can form a pair will r, since if we can
         * make pair with arr[l], we can for sure make with arr[l+1] and
         * arr[l+1]>arr[l].
         * 
         * in above case we should increase r pointer, to get more pairs
         * 
         * we are checking how many pairs we can form that ends at r pointer.
         * 
         * 
         * if above case is false, then we need to move l pointer,
         * if l was not able to form a pair, may be l+1th element will since
         * arr[l+1]>arr[l] and this will decrease difference from lth element
         */
        int l = 0, r = 1;
        int count = 0;
        /**
         * why we have r< arr.length condition?
         * won't this will miss pairs from current l (wherever it is) to r?
         * no. why?
         * we must have increase r (from last element to length) when we got some
         * elements with current r.
         * else we would have been increasing l.
         */
        while (l <= r && r < arr.length) {
            if (arr[r] - arr[l] <= diff) {
                count += r - l;
                r++;
            } else {
                l++;
            }
        }
        return count;

    }
}
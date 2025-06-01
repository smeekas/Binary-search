class Solution {
    public int minimizeArrayValue(int[] nums) {
        int min = Integer.MAX_VALUE, max = -1;
        for (int i : nums) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        int l = min, r = max;
        /**
         * answer will lie between min and max value of array
         * linear search will be O(max*N)
         * binary search will be O((logMax)*N)
         */

        int ans = -1;
        while (l <= r) {
            int newAns = l + (r - l) / 2;
            boolean possible = getAns(nums, newAns);

            if (possible) {
                // newAns is possible
                // possible answer
                // can we do better? we want to minimize newAns
                ans = newAns;
                r = newAns - 1;
            } else {
                // newAns is not possible
                // so increase ans, (we are reducing our ambition)
                // we want to minimize the answer, but since newAns is not possible, we will
                // increase it
                l = newAns + 1;
            }
        }
        return ans;
    }

    boolean getAns(int[] nums, int ans) {
        long[] copy = getCopy(nums);
        if (copy[0] > ans)
            // 0th indexed element cannot be decreased.
            // so if 0th element is > ans, then return false, as we won't be able to achieve
            // {ans} ans
            return false;
        int n = copy.length;
        for (int i = 1; i < n; i++) {

            if (copy[i - 1] <= ans) {
                // how much (i-1)th can increase?
                long howMuch = ans - copy[i - 1]; // this much space (i-1)th element has
                // [4,7,2,2,9,19,16,0,3,15]
                // mik says we should reduce even if result goes negative(his logic)
                // if after decreasing ith element and increasing (i-1)th element, if ith
                // element is > ans that means in future ith element will be ONLY increased. so
                // we won't be able to achieve {ans} ans
                if (copy[i] - howMuch > ans)
                    return false;
                // if we reduce the number and result is > ans means we ans cannot be our answer
                // as in next steps copy[i] can increase only/

                // it is ok, if ith element goes negative, we want to utilize all space as much
                // as we can
                copy[i] -= howMuch; // no need to increase in (i-1)th element (as we won't be using it in future)
                // but we will process ith element so decrease appropriately.
            } else
                return false;

        }
        // if last element is <=ans means we were able to achieve {ans} ans by given
        // operation
        return copy[n - 1] <= ans;
    }

    long[] getCopy(int arr[]) {
        long cp[] = new long[arr.length];
        for (int i = 0; i < arr.length; i++)
            cp[i] = arr[i];
        return cp;
    }

}
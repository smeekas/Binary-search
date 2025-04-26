class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        /**
         * Bruteforce
         * we will try every possible divisor
         * minimum divisor can be 1,
         * maximum divisor can be maximum value. (any other number/maximum number=>
         * result <1 => 1 (result rounded to nearest greater integer))
         * 
         * we will divide array with given divisor and get the result
         * O(n^2)
         */

        /**
         * Optimal
         * why not do binary search on divisor since we already have range?
         * 
         * O(logN*n)
         */
        int max = (int) maximumValue(nums);
        long l = 1, r = max;
        long ans = -1;
        // this is like a finding lowerBound of threshold
        while (l <= r) {
            long newDivisor = l + (r - l) / 2;
            // divide the array with newDivisor and calculate the result
            long result = calc(nums, newDivisor);
            if (result == threshold) {
                // we got the same result.
                // we save the divisor as possible answers
                // since we want to find smallest divisor, we try to get same result with even
                // smaller divisor value. so r = newDivisor-1
                ans = newDivisor;
                r = newDivisor - 1;
            } else if (result > threshold) {
                // result has to be <=threshold. so increase divisor value.
                l = newDivisor + 1;
            } else {
                // result <threshold
                // one of the possible answer since question says result has to be <=threshold
                // can we do better with even smaller divisor value?
                // smaller divisor means our result will increase and we will go close to
                // threshold.
                ans = newDivisor;
                r = newDivisor - 1;
            }
        }
        return (int) ans;
    }

    long calc(int nums[], long divisor) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += divide(nums[i], divisor);
        }
        return sum;
    }

    long divide(int num, long divisor) {
        return (long) Math.ceil((long) num / (double) divisor);
    }

    int maximumValue(int[] num) {
        long max = Integer.MIN_VALUE;
        for (int i : num) {
            max = Math.max(max, i);
        }
        return (int) max;
    }
}
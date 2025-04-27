class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long l = 1, r = getR(workerTimes, mountainHeight);
        /**
         * Bruteforce
         * we can loo through all possible answers from l to r
         * and check if we can mountain height 0 or not.
         * 
         * O(k*n*h)
         * k= maximum value of r
         * n= number of workers
         * h= maximum height
         * n*h = heightInGivenTime function
         */

        /**
         * Optimal
         * why not do binary search on l to r?
         * and why not do binary search in heightInGivenTime on height?
         * 
         * O(logK*n*logH)
         */
        long ans = -1;

        while (l <= r) {
            long newTime = l + (r - l) / 2;
            // newTime. can worker work for given duration? and how much height they can
            // reduce?
            long newHeight = heightInGivenTime(newTime, workerTimes); // they can reduce this much height in given
                                                                      // duration

            // all workers collectively can reduce newHeight in newTime parallelly
            if (newHeight == mountainHeight) {
                // all workers can reduce height same as mountainHeight.
                // possible answer
                // can we do same but in lesser time?
                // for that we do r = newTime - 1;
                ans = newTime;
                r = newTime - 1;
            } else if (newHeight > mountainHeight) {
                // all workers can reduce height > mountainHeight.
                // possible answer
                // we want to reduce only mountainHeight.
                // so we can decrease the time (will also decrease newHeight)
                // for that we do r = newTime - 1;
                ans = newTime;
                r = newTime - 1;
            } else {
                // newHeight < mountainHeight
                // increase the time to increase the newHeight
                l = newTime + 1;
            }
        }
        return ans;
    }

    long getR(int workerTimes[], long h) {
        // time
        int n = workerTimes.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++)
            max = Math.max(max, workerTimes[i]);

        // max*1 + max*2 +max*3 + max*4 => max*(1+2+3+4...)
        return (h * (h + 1) / 2) * max;
    }

    // O(n*LogM)
    long heightInGivenTime(long time, int[] w) {

        // all worker works parallelly
        long ans = 0;
        for (int i : w) {
            /**
             * we don't know how much height they can reduce in given time.
             * From height we can derive time but other way is not possible
             * so we try all possible height efficiently by binary search
             * 
             * timeTakenByWorker*(h*(h+1))/2 = totalTimeToReduceHeight
             * if we know h, we can derive totalTimeToReduceHeight.
             * 
             * but if we know totalTimeToReduceHeight, we cannot derive h because...
             * h^2+h=2*(totalTimeToReduceHeight/timeTakenByWorker)
             * h can have multiple answers
             * 
             * h also have some range, we can do nested binary search!!
             */
            // how much height this worker can reduce in given time?
            long l = 1, r = 100000; // this worker can reduce height in range of 1 to 10^6;
            long localAns = -1;

            // below binary search is similar to finding strict lowerBound
            while (l <= r) {
                long height = l + (r - l) / 2;
                // mid is height
                // time taken by worker is i
                long newTime = i * (height * (height + 1)) / 2; // this work takes newTime to reduce mid height.

                if (newTime == time) {
                    // if times matches
                    // possible answer
                    // we try to reduce more height in given time
                    localAns = height;
                    l = height + 1;
                } else if (newTime < time) {
                    // we managed to reduce given height in timeframe
                    // this height can be one of the answer
                    // we try to reduce more height but within the time frame
                    localAns = height;
                    l = height + 1;
                } else {
                    // newTime > time
                    // to reduce given height we take too much time
                    // try to reduce less height but within the time
                    r = height - 1;
                }
            }
            // if this worker managed reduce the height, collect how much height it managed
            // to reduce
            if (localAns != -1)
                ans += localAns;
        }
        return ans;
    }
}
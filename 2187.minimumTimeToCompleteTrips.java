class Solution {
    public long minimumTime(int[] time, int totalTrips) {
        /**
         * Bruteforce
         * all buses runs in parallel.
         * so maybe we can try all possibilities.
         * we first assume we have time-1.
         * we check how many trips bus can complete in given time and count the trips.
         * if trips are < totalTrips, we try with time+1.
         * when we find time where completed trips are >totalTrips we break the loop and
         * returns the answer
         * O(n^2)
         */

        /**
         * Optimal
         * if we have to loop one by one we don't we use binary search here?
         * minimum time is 1 but what is the maximum time in worst case?
         * well fastest way it can be done is by fastest bus.
         * maximum total time required is time taken by fastest bus to complete
         * totalTrips amount of trips
         * O(logN*n)
         */
        long l = 1, r = (long) minEle(time) * (long) totalTrips;

        long ans = -1;

        while (l <= r) {
            long newTime = l + (r - l) / 2;

            // now we have to check, how many trips we can complete in given newTime
            long numberOfTrips = total(time, newTime);
            // now it is very similar to finding upperBound.
            // finding value >=totalTrips (but strict, just greater then totalTrips)
            if (numberOfTrips == totalTrips) {
                // we can do same number of trips
                // store the answer
                // try to do with lower time, so r=newTime-1
                ans = newTime;
                r = newTime - 1;

            } else if (numberOfTrips > totalTrips) {
                // with given time, we can do more trips, but we want to do only totalTrips
                // save the answers as we are able to achieve totalTrips amount of trips
                // try to do with lower time, so r=newTime-1
                ans = newTime;
                r = newTime - 1;
            } else {
                // numberOfTrips < totalTrips
                // increase the time to do at least totalTrips
                l = newTime + 1;
            }
        }
        return ans;
    }

    long total(int[] time, long currTime) {
        long totalTrips = 0; // total trips that can be completed by all buses
        for (int i = 0; i < time.length; i++) {
            // ex. time limit is 5, bus takes time-2 to complete trip. means bus can do
            // 5/2=> 2.5=> 2 trips
            totalTrips += (long) Math.floor((double) currTime / (float) time[i]);

        }
        return totalTrips;
    }

    int minEle(int[] time) {
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < time.length; i++) {
            max = Math.min(max, time[i]);
        }
        return max;
    }
}
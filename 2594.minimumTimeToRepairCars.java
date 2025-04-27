class Solution {
    public long repairCars(int[] ranks, int cars) {
        long l = 1, r = getMax(ranks) * (long) Math.pow((long) cars, 2);
        /**
         * Bruteforce
         * all mechanics works simultaneously
         * we have to repair {cars} cars
         * maybe we can take some time-t and see whether they can repair all cars in
         * time-t or not.
         * can we have range of time? yes!
         * minimum time taken can be 1.
         * maximum time taken can be that mechanic will lowest rank alone repairing all
         * cars
         * 
         * now we can loop through l to r and check one by one if they can complete the
         * repair or not
         * 
         * O(k*n)
         * k= maximum time mechanic will lowest rank will take to repair all cars
         */

        /**
         * Optimal
         * wny don't we do binary search from l to r?
         * O(logK*n)
         */
        long ans = -1;
        while (l <= r) {
            long newTime = l + (r - l) / 2;
            // how many cars do mechanics can repair in newTime?
            long totalCars = getCarsInTime(ranks, newTime);
            if (totalCars == cars) {
                // they can repair all cars in given time newTime!
                // possible answer
                // can be do same in still lower time?
                // for that we do r = newTime - 1;
                ans = newTime;
                r = newTime - 1;
            } else if (totalCars < cars) {
                l = newTime + 1;
            } else {
                // totalCars > cars

                // they can repair more cars in given time newTime!
                // possible answer
                // we only want to repair {cars} amount of cars
                // for that we do r = newTime - 1;
                // {it will decrease number of cars repaired in newTime}
                ans = newTime;
                r = newTime - 1;
            }
        }
        return ans;

    }

    long getCarsInTime(int[] ranks, long time) {
        // rank*cars^2=totalTime
        // cars=sqrt(totalTime/rank)
        // we have to take Math.floor as they cannot repair 2.8 cars but 2 cars

        // what totalCars is?
        // it is how many cars mechanics can repair simultaneously in time {time}
        long totalCars = 0;
        for (int rank : ranks) {
            totalCars += Math.floor(Math.sqrt((double) time / (double) rank));
        }
        return totalCars;
    }

    long getMax(int[] ranks) {
        int max = Integer.MIN_VALUE;
        for (int rank : ranks)
            max = Math.max(max, rank);
        return max;
    }
}
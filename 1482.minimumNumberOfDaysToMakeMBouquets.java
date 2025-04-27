class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        // if we don't have enough flowers to make m bouquet, then return -1
        if ((long) m * k > bloomDay.length)
            return -1;

        /**
         * Bruteforce
         * do answer have any range?
         * maybe yes.
         * minimum it can take us 1 days
         * maximum it can take us maximum amount of time any flower can take to bloom
         * 
         * we can iterate through l to r and check foe given day, how many bouquets we
         * can make
         * 
         * O(k*n)
         * k=maximum days any flower can take to bloom
         */

        /*
         * Optimal
         * since we have range of answers, why don't we binary search on answer?
         * 
         * O(logK*n)
         */
        int l = 1, r = getMax(bloomDay);
        int ans = -1;
        while (l <= r) {
            int day = l + (r - l) / 2;
            // how many bouquets can be built in {days} days?
            int bou = bouquetInGivenDay(bloomDay, day, k);
            if (bou == m) {
                // we can create same amount of bouquets!
                // possible answer
                // can we do better? same bouquets in less time?
                // for that we will check with r = day - 1;
                ans = day;
                r = day - 1;
            } else if (bou < m) {
                // we have to do better, increase the days so that more flower can bloom and we
                // can make m bouquets
                l = day + 1;
            } else {
                // bou > m

                // we can create more amount of bouquets then m!
                // possible answer
                // can we do better?
                // we only want m bouquets and not more then m bouquets
                // for that we will check with r = day - 1;
                // it will decrease the days, (will also decrease number of bouquets can be
                // constructed)
                ans = day;
                r = day - 1;
            }
        }
        return ans;
    }

    int bouquetInGivenDay(int[] bloom, int day, int k) {
        int flowerCount = 0;
        int bouquetCount = 0;
        for (int b : bloom) {
            if (day >= b) {
                // flower bloomed;
                flowerCount++;
                if (flowerCount == k) {
                    // new bouquets can be constructed
                    bouquetCount++;
                    flowerCount = 0;
                }

            } else {
                // also we want to take adjacent flowers
                // so if current flower fails( have not bloomed in {day}), then we have to reset
                // flowerCount
                flowerCount = 0;
            }
        }
        return bouquetCount;
    }

    int getMax(int[] bloom) {
        int max = Integer.MIN_VALUE;
        for (int b : bloom) {
            max = Math.max(max, b);
        }
        return max;
    }
}
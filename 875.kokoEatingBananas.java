class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        long l = 1, r = max(piles);
        // slowest rate at which koko can eat banana=>1
        // fastest rate at which koko can eat banana => maximum pile size

        /**
         * Bruteforce
         * loop through l to r and get how many hours koko requires to eat all bananas
         * if hours are < h then break loop and return answer.
         * here we want to minimize value-K, such that koko can eat all bananas within h
         * O(n^2)
         */

        /**
         * Optimal
         * why not binary search from l to r
         */
        long ans = -1;
        while (l <= r) {
            long speed = l + (r - l) / 2;
            // get hours taken by koko by eating all bananas at speed
            long hours = getHours(piles, speed);
            if (hours == h) {
                // if hours are same then we store k(speed) as possible answer
                // we were able to eat all bananas in h, can we do better?
                // can we eat all bananas with smaller k(lower speed)? (can increase hours)
                // for that we do r = speed - 1
                ans = speed;
                r = speed - 1;
            } else if (hours < h) {
                // if hours are < h, then we store k(speed) as possible answer
                // we were able to eat all bananas in < h, can we do better?
                // can we eat all bananas with smaller k(lower speed)? (will increase hours)
                // for that we do r = speed - 1
                ans = speed;
                r = speed - 1;
            } else {
                // hour > h
                // we need to increase our speed to eat all bananas in <=h
                l = speed + 1;
            }
        }
        return (int) ans;
    }

    long getHours(int piles[], long speed) {
        long totalHours = 0;
        for (int i : piles) {
            if (i < speed) {
                // pile size is < speed, means we can eat all bananas of this pile in one hour.
                totalHours++;
            } else {
                // pile size > speed, means it can take ceil(i/speed) hours to eat all bananas
                // of this pile
                totalHours += (long) Math.ceil((double) i / (double) speed);
            }
        }
        return totalHours;
    }

    long max(int[] piles) {
        long max = Long.MIN_VALUE;
        for (int i : piles)
            max = Math.max(i, max);
        return max;
    }
}
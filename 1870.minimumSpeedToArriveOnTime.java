class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {
        int l = 1, r = 10000001; // as given in the question
        /**
         * Bruteforce
         * from speed 1 to speed 10000001, we will try every speed and get how much time
         * it requires.
         * we will check if time requires is < hour? if yes, then we found our answer
         * O(n^2)
         */

        /**
         * Optimal
         * why don't we do binary search on speed?
         * O(logN*n)
         */
        int ans = -1;
        while (l <= r) {
            int newSpeed = l + (r - l) / 2;
            double newTime = getTime(dist, newSpeed);

            if (newTime == hour) {
                // with given speed we can reach destination in given hours
                // we can store it as possible answer
                // can we achieve same with lesser speed? since we want minimum speed?
                // for that we do r= newSpeed-1
                ans = newSpeed;
                r = newSpeed - 1;
            } else if (newTime < hour) {
                // with given speed we can reach destination quicker then hour!
                // but it is ok if we reach in hours. so we can decrease our speed to check
                // further.
                // still newSpeed is one of the possible answer
                ans = newSpeed;
                r = newSpeed - 1;
            } else {
                // newTime > hour
                // with given speed we cannot reach destination in given hour.
                // increase the speed
                l = newSpeed + 1;
            }
        }
        return ans;
    }

    double getTime(int[] dis, int speed) {
        double time = 0;
        for (int i = 0; i < dis.length - 1; i++) {
            // we have to wait for next integer
            // so we can taking ceil directly
            time += Math.ceil((double) dis[i] / (double) speed);
        }
        // for the last element we do not have to take ceil
        time += (double) dis[dis.length - 1] / (double) speed;
        return time;
    }
}
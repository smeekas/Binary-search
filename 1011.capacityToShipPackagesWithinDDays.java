class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int l = getLeft(weights), r = getRight(weights);
        /**
         * we need to return capacity of ship. (maximum how much weight ship will carry
         * each day)
         * we have deadline of days
         * maybe we can try all possible cases?
         * can there be any range?
         * yes!
         * getLeft and getRight
         */
        /**
         * Bruteforce
         * loop from l to r and check.
         * O(n^2)
         */

        /**
         * Optimal
         * why don't do binary search on l to r?
         * 
         * O(logN*n)
         */
        int ans = -1;

        while (l <= r) {
            int newCap = (l + r) / 2;
            // now we try to get number of days it will take ship per day with given
            // capacity
            int daysWithCurrCap = getDays(weights, newCap);

            if (daysWithCurrCap == days) {
                // if number of days required is same as newly calculated capacity, then we
                // found possible answer
                // still we can check whether it is possible to achieve days (deadline) with
                // lesser weight or not, so we do r = newCap - 1
                ans = newCap;
                r = newCap - 1;
            } else if (daysWithCurrCap > days) {
                // we cannot achieve deadline, so increase the capacity
                l = newCap + 1;
            } else {
                // daysWithCurrCap < days
                // with current weight we can finish shipping within deadline
                // newCap is possible answer
                // can we decrease the capacity (means increase number of days) such that they
                // are still < days? for that we do r = newCap - 1
                ans = newCap;
                r = newCap - 1;

            }
        }
        return ans;
    }

    int getDays(int w[], int cap) {
        int daysTaken = 0; // variable to keep count of days
        int total = 0; // variable to keep count of weight
        for (int i : w) {

            if ((total + i) > cap) {
                // if current total weight + this weight (i) is more then ship's capacity
                // then i cannot be taken.
                // for total one day required so increment to daysTaken
                daysTaken++;
                total = i; // new total for current weight
            } else {
                // adding i to total is less then cap, so i can be counted in current day's
                // capacity
                total += i;
            }
        }
        // now total still can have some value.
        // for that we add + 1
        // for the remaining weights, one more day
        return daysTaken + 1;
    }

    int getLeft(int[] w) {
        // minimum ship's capacity can be same as least weight in given array
        int max = Integer.MIN_VALUE;
        for (int i : w)
            max = Math.max(max, i);
        return max;
    }

    int getRight(int[] w) {
        // maximum ship's capacity can be sum of all weights in given array
        int total = 0;
        for (int i : w)
            total += i;
        return total;
    }
}
class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long total = sum(batteries);
        long l = 1, r = total / (long) n;
        /**
         * in the best case we can utilize all the juice from battery for all PCs
         * so totalSum/numberOfPcs
         * 
         * in worst case we can support for 1 unit of time
         * 
         * we can try each time {t}, and check how many computers can be supported for
         * time {t}
         */
        long ans = -1;
        // l, r are time
        while (l <= r) {
            long newTime = l + (r - l) / 2;
            // how many computers we can run for given newTime?
            long totalPcs = getComputers(batteries, newTime);
            if (totalPcs == n) {
                // in given time, we can run as given computers.
                // possible answer
                // can we run again n computers in more time?
                // increasing time may decrease totalPcs
                // for that we increase time l=newTime+1;
                ans = newTime;
                l = newTime + 1;
            } else if (totalPcs > n) {
                // in given time, we can run > n computers.
                // possible answer
                // but we just want to run n computers.
                // increasing time may decrease totalPcs
                // for that we increase time l=newTime+1;
                ans = newTime;
                l = newTime + 1;
            } else {
                // totalPcs < n
                // for given time {newTime}, we were not able to run n pcs
                // decrease time
                r = newTime - 1;
            }
        }
        return ans;
    }

    long getComputers(int[] batteries, long time) {

        long totalPower = 0;
        for (int bat : batteries) {
            if (bat >= time) {
                // assume this battery will be used to power one computer during entire {time}
                // time
                // so we add {time} time
                // why not add entire duration?
                // we want to run computer with this battery's power for {time} time.
                // so anyway we will be running computer's simultaneously. so extra juice is of
                // no use
                totalPower += time;
            } else {
                // since this battery cannot power entire computer for {time} time, we add
                // whatever this battery can contribute
                totalPower += bat;
            }
        }
        // totalPower- Total Power we have
        // time- how long we want to run computer
        // totalPower / time- how many computer's can we run for {time} time
        return totalPower / time;

        // below approach do not encounter for the fact that same battery cannot be
        // shared simultaneously
        // if(time*N <= total)return true;
        // return false;

    }

    long sum(int arr[]) {
        long ans = 0;
        for (int i : arr)
            ans += i;
        return ans;
    }
}
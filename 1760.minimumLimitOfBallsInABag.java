class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
        // bruteforce is that we keep maxHeap, and perform operation one by one till
        // maxOperations

        // binary search on answer!!!
        // best-case- every number divided into 1
        // worst-case- no operation performed. (max number will be max number in arr)
        /**
         * Dividing is unpredictable
         * we may try all possible cases of dividing number
         * instead easier way is we have some division available and check if it is
         * correct or not
         * 
         * that is why binary Search is very useful here
         * 
         * Linear search: O(max*N)
         * binary search: O((logMax)*N)
         */
        int l = 1, r = getMax(nums);
        int ans = 0;
        while (l <= r) {
            int newMax = l + (r - l) / 2;
            long operationReq = getOperations(newMax, nums);

            // how many operations required if we want to divide array such that max number
            // is newMax
            if (operationReq == maxOperations) {
                // we got exact answer!
                // can we do better?
                // can we have same operation but even lower number?
                // since we want to minimize the maximum(operationReq) number
                ans = newMax;
                r = newMax - 1;
            } else if (operationReq > maxOperations) {
                // we can do maximum {maxOperations}
                // increase max number, which will cause less operations
                l = newMax + 1;
            } else {
                // operationReq < maxOperations
                // we are using less operations
                // possible answer!
                // can we increase number of operations but can even smaller max number?
                // r=newMax-1 will decrease max number, and cause more operations
                // but we are allowed to do {maxOperations} operations
                ans = newMax;
                r = newMax - 1;
            }
        }
        return ans;
    }

    long getOperations(int max, int[] arr) {

        // ex. 8 and max is 2
        // 8-> {6,2}
        // 6-> {4,2}
        // 4-> {2,2}
        // total 3 operations required 8/2-1
        // 3 and max is 2.
        // 3/2-> 1.5 -> 2 -> 2-1=1 operation required. means we have to take ceil
        // 3->{2,1}
        long opCount = 0;
        for (int i : arr) {
            if (i > max) {

                opCount += Math.ceil(i / (double) max) - 1;
            }
        }
        return opCount;
    }

    int getMax(int[] arr) {
        int max = -1;
        for (int i : arr) {
            max = Math.max(max, i);
        }
        return max;
    }
}
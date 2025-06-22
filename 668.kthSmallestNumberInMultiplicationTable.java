class Solution {
    public int findKthNumber(int m, int n, int k) {
        // bruteforce will be to create a table

        int l = 1, r = m * n;
        int ans = -1;
        while (l <= r) {

            int mid = l + (r - l) / 2;
            int newK = getK(mid, m, n);
            if (newK == k) {
                // means {mid} have k smaller element on left side
                // possible answer and we need to search on left. why?
                // possible answer becuase it has k smaller element on left side
                // we need to search on left because, matrix contains duplicate
                // and {mid} can be a number which do not exists in matrix
                // so mid=7 can have k smaller element on left side, but tightest 6 which exists
                // in table can also have k smaller element on left side
                ans = mid;
                r = mid - 1;
            } else if (k > newK) {
                // we want to increase newK
                // current newK is smaller then actual k
                l = mid + 1;
            } else {
                // k < newK
                // {mid } have more then k element on left side.
                // technically it has k element on left side.
                // store it as possible answer.
                // we will for sure find better element on left side so search on left side
                ans = mid;
                r = mid - 1;
            }

        }
        return ans;
    }

    int getK(int mid, int m, int n) {
        // we can check for every row how many number are smaller then mid
        int i = 1;
        int count = 0;
        while (i <= m) {

            /**
             * i*n is last element in current row
             * if mid is greater then that means entire row is small
             * if i is greater then mid then that means entire row is greater then mid so no
             * count
             * 
             * number are in below format
             * 1*i 2*i 3*i 4*i ..... n*i
             * 
             * how many number are smaller then mid?
             * k*i <= mid
             * k<=mid/i;
             * k will be the multiplier of i
             * 
             * but also k need to be smaller then n too
             * it cannot go beyond n. why?
             * every row can have max n elements
             * if i is 1 and mid is 100
             * then k will be 100. it may happen that we have < 100 element per row
             * 
             */
            if (i * n <= mid) {
                count += n;
            } else if (i > mid) {
                // nothing
            } else {
                count += Math.min(mid / i, n);
            }
            i++;
        }
        return count;
    }
}
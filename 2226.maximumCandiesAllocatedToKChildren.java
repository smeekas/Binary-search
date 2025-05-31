class Solution {
    public int maximumCandies(int[] candies, long k) {
        // on size of sub-piles
        int l = 1, r = getMax(candies);
        /**
         * we have range of answer.
         * we can go one by one n^2
         * or we can do binary search n*logN
         */
        int ans = 0;
        while (l <= r) {
            int pileSize = l + (r - l) / 2;
            long piles = getNumberOfPiles(candies, pileSize);
            if (piles == k) {
                ans = pileSize;
                // with given pile size we are able to distribute to exact k children
                // can we give more candies?
                l = pileSize + 1;
            } else if (piles > k) {
                ans = pileSize;
                // with given pile size we are able to distribute to more children
                // can we increase number of candies and decrease number of children?
                // this way we can maximize the number of candies
                l = pileSize + 1;
            } else {
                // piles < k
                // we are not able to distribute to K children
                // decrease the pileSize, this will increase number of children
                r = pileSize - 1;

            }
        }
        return ans;
    }

    long getNumberOfPiles(int arr[], int pileSize) {
        long piles = 0;
        for (int i : arr) {
            if (pileSize <= i) {
                piles += i / pileSize;
                // if number of piles are x.y then we have x piles
            } else {

                // each pile should be of size pileSize, we not then we cannot consider it
            }
        }
        return piles;
    }

    int getMax(int arr[]) {
        int max = Integer.MIN_VALUE;
        for (int i : arr)
            max = Math.max(max, i);
        return max;
    }
}
class Solution {
    public int minimizedMaximum(int n, int[] quantities) {
        // minimize the maximum,maximize the minimum => Binary Search on answer!!

        // for(long cap=1;cap<10000000;cap++){
        // we will try every ans
        // }
        int max = -1;
        for (int i : quantities)
            max = Math.max(max, i);
        int l = 1, r = max; // any store can have maximum quantity of ith product
        int ans = 0;
        while (l <= r) {
            int cap = l + (r - l) / 2;
            // how many stores we can have if every store can get maximum cap products
            int stores = getStores(quantities, cap);
            // more cap, less stores

            if (stores == n) {
                // we have exact number of stores!
                // possible answer
                // we want to minimize "maximum number of quantity"
                // can we have same stores but with lesser capacity?
                // r= cap-1;
                ans = cap;
                r = cap - 1;
            } else if (stores < n) {

                // some stores can have 0 products
                // possible answer!
                // can we have close to n stores?
                // decrease the capacity
                // r=cap-1;
                ans = cap;
                r = cap - 1;
            } else {
                // stores > n
                // nah, we want <=n stores
                // increase the capacity, so that number of stores gets reduced
                l = cap + 1;
            }
        }
        return ans;
    }

    int getStores(int quan[], int cap) {
        int stores = 0;
        for (int quantity : quan) {
            // 7 quantity, 2 capacity
            // 7/2 => 3.5 => 4 stores 3 stores with 2 quantity and 1 store with 1 quantity.
            stores += Math.ceil((float) quantity / (float) cap);
        }
        return stores;
    }
}
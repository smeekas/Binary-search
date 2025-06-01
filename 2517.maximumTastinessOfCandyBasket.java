import java.util.Arrays;

class Solution {
    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int n = price.length;
        int l = 0, r = price[n - 1] - price[0];
        // minimum difference 0
        // maximum difference (price[n - 1] - price[0]) since array is sorted

        // this difference is tastiness range
        int ans = 0;
        while (l <= r) {
            int tasty = l + (r - l) / 2;
            int candies = getCandies(price, tasty);
            if (candies == k) {
                ans = tasty;
                // can we do better?
                // more tastyNess with same candies
                l = tasty + 1;
            } else if (candies > k) {
                // we wanted k candies, but got more
                // we can reduce candies, to reduce it we can increase tastyNess
                ans = tasty;
                l = tasty + 1;
            } else {
                // candies < k
                // we are not able to get k candies.
                // reduce tastyNess, which will increase number of candies
                r = tasty - 1;
            }
        }
        return ans;
    }

    /**
     * tastyNess is smallest absolute difference between any two values
     * we have sorted the array to help use find difference
     * 
     * now we have to maximize it
     * 
     * so why not pick any tastyNess and verify if we can get it or not?
     * 
     */
    int getCandies(int price[], int tasty) {
        int candies = 1;
        int n = price.length;
        int curr = price[0];
        // why take first candy first?
        // because it we think of using second candy first then our we will only reduce
        // our tastyNess.
        // for ex. a b c d
        // if |a-c| always will be higher then |b-c| since our array is sorted!

        // we want to get as many candy as we get with given tastyNess
        // here tastyNess is difference between values
        for (int i = 1; i < n; i++) {
            if (Math.abs(price[i] - curr) >= tasty) {
                // diff is greater then tastyNess, so one more candy
                candies++;
                curr = price[i]; // update new price, which we will use in later iteration to check tastyNess
                                 // (difference)
            }

        }
        return candies;
    }
}
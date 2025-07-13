import java.util.Arrays;

class Solution {
    public int maxNumOfMarkedIndices(int[] nums) {
        int n = nums.length;
        // index i , j's order do not matter. so we can sort it
        Arrays.sort(nums);
        int l = 0, r = n / 2;
        // this range defines point, where on left side we will have our number, whose
        // paired number exists on right side
        // pair cannot exists beyond n/2;
        // ex. for number beyond n/2, pair will exists outside of n
        // because from 0 to n/2 numbers, we need n/2 number of right side to form a
        // pair

        // answer exists in below format
        // RRRRRRRRRRWWWWWWWWWWWW
        // R-right
        // W-wrong

        // O(logN*N)
        int ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            boolean isPossible = getPair(nums, mid);
            // if mid number of pair possible?
            if (isPossible) {
                // yes!
                // can we do better?
                // search on right side
                ans = mid;
                l = mid + 1;
            } else {
                // search on left side
                // as answer do not exists on mid or on right side
                r = mid - 1;
            }
        }
        // double it since it it will form a pair
        return ans * 2;
    }

    boolean getPair(int arr[], int pairs) {
        int n = arr.length;
        int pairCount = 0;
        int i = 0, j = pairs;
        // pair is our middle line
        while (i < pairs && j < n) {

            if (arr[i] * 2 <= arr[j]) {
                // it satisfies increment the pairCount
                i++; // as well the i
                pairCount++;
            }
            // if it didn't satisfied we still will increment the j
            // to find next number
            j++;
        }
        // if the middle point {pair} managed to find pair for all left side numbers?
        return pairCount == pairs;
    }
}

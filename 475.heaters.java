import java.util.Arrays;

class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        int n = houses.length, m = heaters.length;
        /**
         * Bruteforce
         * we can find nearest heater for every house
         * maximum distance from any house will be our answer
         * O(n*m)
         */

        /**
         * Optimal
         * since houses and heaters are number-line,
         * we can sort them
         * and then for every house, we can find nearest heater via binary search
         * O(n*logN + m*logM + n*logM)
         * (first two for sort, last one for binary search)
         */
        long ans = 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        for (int i = 0; i < n; i++) {
            // what is the nearest heater?
            long heaterDistance = -1;
            int lo = lowerBound(heaters, houses[i]);
            int up = upperBound(heaters, houses[i]);

            if (lo != -1 && up != -1) {
                heaterDistance = Math.min(houses[i] - heaters[lo], heaters[up] - houses[i]);
            } else if (lo != -1) {
                heaterDistance = houses[i] - heaters[lo];
            } else if (up != -1) {
                // up!=-1
                heaterDistance = heaters[up] - houses[i];
            }
            ans = Math.max(ans, heaterDistance);

        }
        return (int) ans;
    }

    // strict
    int lowerBound(int arr[], int ele) {
        int l = 0, r = arr.length - 1;
        int ans = -1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == ele) {
                ans = mid;
                l = mid + 1;
            } else if (arr[mid] > ele) {
                r = mid - 1;
            } else {
                // arr[mid] < ele
                ans = mid;
                l = mid + 1;
            }
        }
        return ans;
    }

    // strict
    int upperBound(int arr[], int ele) {
        int l = 0, r = arr.length - 1;
        int ans = -1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] == ele) {
                ans = mid;
                r = mid - 1;
            } else if (arr[mid] > ele) {
                ans = mid;
                r = mid - 1;
            } else {
                // arr[mid] < ele
                l = mid + 1;
            }
        }
        return ans;
    }
}
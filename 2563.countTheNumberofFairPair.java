import java.util.Arrays;

class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        // i<j is restriction but nums[i]+nums[j] === nums[j]+ nums[i];
        // so this condition can be ignored. condition will be now i!=j
        long ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int curr = nums[i];
            // lower<=curr+nums[j]<=upper
            // we need to find lower-curr and upper-curr. they must be in lower---upper
            // range. let's say result is x & y respectively.
            // ans will be y-x+1; x & y are indexes from nums. we can take all number from x
            // to y to make pair
            // we will try all possible combination of nums[j] such that it is greater than
            // lower but lesser than upper (because lower and upper are our limits)
            // so we are applying binary search to find upperBound of lower-curr in array
            // and lowerBound of upper-curr.
            // for [2,4,6,8,10] and to find 5 we have to take 6(which is upperBound) ( 5 is
            // lower-curr OR LEFT VALUE)
            // to find 9 (9 is upper-curr OR RIGHT VALUE) we need to take 8 (which is lower
            // bound)
            int left = upperBound(nums, i + 1, n - 1, lower - curr);
            int right = lowerBound(nums, i + 1, n - 1, upper - curr);
            if (left == -1 || right == -1) {
                continue;
            }
            // (right - left + 1) is count of all nums[j] where lower<=curr+num[j]<=upper
            ans += (right - left + 1);

        }
        return ans;
    }

    /**
     * logic of uppercase and lowercase is same as 2080 range frequency queries
     */

    int upperBound(int arr[], int l, int r, int num) {
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int curr = arr[mid];
            if (curr == num) {
                ans = mid;
                r = mid - 1;
            } else if (curr < num) {
                l = mid + 1;
            } else {
                // curr > num
                ans = mid;
                r = mid - 1;
            }
        }
        return ans;
    }

    int lowerBound(int arr[], int l, int r, int num) {
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int curr = arr[mid];
            if (curr == num) {
                ans = mid;
                l = mid + 1;
            } else if (curr < num) {
                ans = mid;
                l = mid + 1;
            } else {
                // curr > num
                r = mid - 1;
            }
        }
        return ans;
    }
}

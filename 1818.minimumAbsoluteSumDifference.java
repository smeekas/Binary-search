import java.util.Arrays;

class Solution {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        // bruteforce.
        // find diff array
        // for every element, search in array for better replacement
        // this will be n^2

        /*
         * optimal
         * we can find sum of their differences first
         * for every nums2[i], we try to find closest number in nums1 array.
         * now since we just want to find closest number in nums1, we can sort nums1 to
         * leverage binary search.
         * when we find closest number, we try to calculate new sum
         */
        int[] sort = nums1.clone();
        Arrays.sort(sort); // nlogn

        int n = nums1.length;
        int diff[] = new int[n];

        long originalSum = 0;
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]); // n
            originalSum = originalSum + diff[i];
        }

        long ans = Long.MAX_VALUE;
        int mod = (int) 1e9 + 7;

        for (int i = 0; i < n; i++) {
            // we try to find both lowerBound and upperBound in original nums1 array
            int betterL = lowerBound(sort, nums2[i]);
            int betterR = upperBound(sort, nums2[i]);
            long bestDiff = Long.MAX_VALUE;

            if (betterL != -1 && betterR != -1) {
                // if both of them are present then take the one with smallest difference
                bestDiff = Math.min(Math.abs(betterL - nums2[i]), Math.abs(betterR - nums2[i]));
            }
            if (betterR != -1) {
                // if only upperBound is present then take it
                bestDiff = Math.min(Math.abs(betterR - nums2[i]), bestDiff);
            }
            if (betterL != -1) {
                // if only lowerBound is present then take it
                bestDiff = Math.min(Math.abs(betterL - nums2[i]), bestDiff);
            }
            // remove current difference and add new difference to get new sum
            long newSum = (originalSum - diff[i]) + bestDiff;
            if (newSum < originalSum && newSum < ans) {
                // if new sum is lesser than original and it is lesser than current ans
                // take newSum as new answer
                ans = newSum;
            }
        }

        // if answer is not present then return 0 else return ans with mod
        return ans == Long.MAX_VALUE ? 0 : (int) (ans % mod);

    }

    int lowerBound(int[] arr, int num) {
        int l = 0, r = arr.length - 1;

        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midEle = arr[mid];
            if (midEle == num) {
                ans = mid;
                l = mid + 1;
            } else if (midEle < num) {
                ans = mid;
                l = mid + 1;
            } else {
                // midEle > num
                r = mid - 1;
            }
        }
        if (ans == -1)
            return -1;
        return arr[ans];
    }

    int upperBound(int[] arr, int num) {
        int l = 0, r = arr.length - 1;
        int ans = -1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midEle = arr[mid];
            if (midEle == num) {
                ans = mid;
                r = mid - 1;
            } else if (midEle < num) {

                l = mid + 1;
            } else {
                // midEle > num
                ans = mid;
                r = mid - 1;
            }
        }
        if (ans == -1)
            return -1;
        return arr[ans];
    }
}

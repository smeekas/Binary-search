class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int l = 0, r = arr.length - 1;

        int ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midEle = arr[mid];
            if (midEle <= arr[mid + 1]) {
                // numbers are in increasing order
                // try to find on right side.
                l = mid + 1;
            } else {
                // midEle > arr[mid+1]

                // number are in decreasing order.
                // strict decrease.

                // we do not know about mid -1.
                // so mid can be answer for us. (we will guaranteed have ans. since right side
                // is decreasing, answer must exists in left side)
                // store is as possible answer and search on left side.
                // mid now can be our ans since left side is unknown to us
                // anyway mid as ans will be overridden
                ans = mid;
                r = mid - 1;
            }
        }
        return ans;
    }

    int shit(int[] arr) {
        int l = 0, r = arr.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midEle = arr[mid];
            if (mid == 0) {
                l = mid + 1;
            } else if (mid == arr.length - 1) {
                r = mid - 1;
            } else if (midEle > arr[mid - 1] && midEle < arr[mid + 1]) {
                // increasing
                l = mid + 1;
            } else if (arr[mid - 1] > midEle && midEle > arr[mid + 1]) {
                // decreasing
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
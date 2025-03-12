import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        // order of arr2 do not matter so we can sort ar2 to leverage binary search
        Arrays.sort(arr2);
        int n = arr1.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // for number arr[i] we want to find difference between arr[i] and all element
            // of arr2. if there is no element where difference is <= d then that is our
            // answer

            // we do binary search on arr2 and difference to be <=d it has to be between
            // arr[i]-d && arr[i]+d
            // so we are passing entire range. if we cannot find number in given range then
            // we are good to go!
            if (binarySearchRange(arr2, arr1[i] - d, arr1[i] + d)) {
                ans++;
            }
        }
        return ans;
    }

    boolean binarySearchRange(int[] arr, int from, int to) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= from && arr[mid] <= to) {
                // if number is in range then difference is <=d so current arr[i] is cannot be
                // counted in answer
                return false;
            } else if (arr[mid] < from) {
                // our number is outside from-to range. so try to search on right half
                l = mid + 1;
            } else if (arr[mid] > to) {
                // our number is outside from-to range. so try to search on left half
                r = mid - 1;
            }
        }
        return true;
    }

}

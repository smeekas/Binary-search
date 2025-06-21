/**
 * This is MountainArray's API interface.
 * You should not implement it, or speculate about its implementation
 */
interface MountainArray {
    public int get(int index);

    public int length();
}

class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int n = mountainArr.length();
        int l = 0, r = n - 1;
        int peak = -1;
        // find a peak element
        // do binary search on first half; 0th index to peak
        // do binary search on second half; peak to (n-1)th index

        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midEle = mountainArr.get(mid);
            int prevEle = mid == 0 ? -1 : mountainArr.get(mid - 1);
            int nextEle = mid == n - 1 ? -1 : mountainArr.get(mid + 1);
            if (midEle > prevEle && midEle > nextEle) {
                peak = mid;
                break;
            } else if (prevEle > nextEle) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if (mountainArr.get(peak) == target)
            return peak;
        l = 0;
        r = peak - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midEle = mountainArr.get(mid);
            if (midEle == target)
                return mid;
            else if (midEle > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        l = peak + 1;
        r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midEle = mountainArr.get(mid);
            if (midEle == target)
                return mid;
            else if (midEle > target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }
}
class Solution {
    public int findMin(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int N = nums.length;
        int l = 0, r = N - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int midEle = nums[mid];
            if (midEle > nums[r]) {
                // if mid element is greater then nums[r]
                // means array is sorted on left side
                // we want to find min (start point of array)
                // so we will search on unsorted part
                l = mid + 1;
            } else {
                // array is sorted on right side
                // search on unsorted part (left part)
                // why mid and not mid-1?
                // we haven't ruled out mid element yet.
                // we need to consider that as well when we search on left
                r = mid;
            }
        }
        // after loop is broken l will point to starting point of array(min ele)
        return nums[l];
    }
}
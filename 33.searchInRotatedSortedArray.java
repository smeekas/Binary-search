class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < nums[r]) {
                // right half is sorted
                // now need to check if element is part of sorted group or not.
                // we can easily check that
                if (target >= nums[mid] && target <= nums[r])
                    l = mid + 1; // target lies in sorted
                /**
                 * why two condition?
                 * just target >=nums[mid] is not enough?
                 * in normal binary search it works because we know that array is in increasing
                 * order
                 * it can be part of array that is sorted
                 * for ex. 5 6 7 8 9 0 1 2 3 4
                 * if mid is 1 and r is 3 then if target is greater then 1 do not mean it is on
                 * right side. so we need to check range. (range is rotated. so if target>mid
                 * but target can exists in front of mid. due to rotating)
                 * 
                 * 
                 */
                else
                    r = mid - 1; // target do not lie in sorted half
            } else {
                // left half is sorted

                if (target >= nums[l] && target <= nums[mid])
                    r = mid - 1;
                else
                    l = mid + 1;
            }
        }
        return -1;
    }
}
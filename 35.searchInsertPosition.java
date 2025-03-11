class Solution {
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                // same element then this will be position
                return mid;
            } else if (nums[mid] < target) {
                // if current element is smaller than target then we have to search on right
                l = mid + 1;
            } else {
                // if current element is larger than target then we have to search on left
                r = mid - 1;
            }
        }
        // here l>r. more preciously, l==r+1 (r+1>r so condition broke)

        // after l==r==mid
        // .....if we did l=mid+1, curr-number < target
        // ..........it means that next position of curr-number mid+1(which is now l) is
        // our best position. now loop would have been broken
        // .....if we did r=mid-1, curr-number > target
        // ..........it means that current position of curr-number (which is mid or l)
        // is our best position.why? because our target number is still smaller than
        // current number, so we should search on left part so we will do r=mid-1. But,
        // since l==r==mid, there is nothing on left part that is smaller than our
        // target. that means current position is the best position

        // so l is the best position to insert
        return l;
    }
}
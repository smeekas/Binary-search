class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        int ans = -1;
        if (nums.length == 1)
            return nums[0];
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (mid % 2 == 0) {
                // even mid means
                // mid is first number and mid+1 is second number
                // if that do not satisfy that means there is issue on left part due to which
                // current number's position is mis-matched
                if (mid == n - 1) {
                    // if current number is last one, then it's paired element do not exists
                    return nums[mid];
                } else if (nums[mid] == nums[mid + 1]) {
                    // both are same.
                    // no issue so far till here
                    // search on right side
                    l = mid + 1;
                } else {
                    // issue detected.
                    // some number is missing on left side due to that number mis-matched
                    // search on left side
                    // we do not know if mid created issue or not (left side of mid is unknown to
                    // us)
                    // store mid element as possible answer for causing mis-matched
                    ans = nums[mid];
                    r = mid - 1;
                }
            } else {
                // if mid is odd then it must match with it's prev number
                if (nums[mid] == nums[mid - 1]) {
                    // number matched.
                    // no issue so far till here
                    // search on right side
                    l = mid + 1;
                } else {
                    // issue detected.
                    // some number is missing on left side due to that number mis-matched
                    // search on left side
                    // we do not know if mid created issue or not (left side of mid is unknown to
                    // us)
                    // store mid element as possible answer for causing mis-matched
                    ans = nums[mid];
                    r = mid - 1;
                }
            }
        }
        return ans;
    }
}
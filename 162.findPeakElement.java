class Solution {
    public int findPeakElement(int[] nums) {
        /**
         * O(n) solution is to loop through the array and check adjacent.
         */
        int n = nums.length;
        if (n == 1)
            return 0;
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midEle = nums[mid];
            // if mid-1 or mid+1 is out of array then treat it as -1
            int left = mid <= 0 ? Integer.MIN_VALUE : nums[mid - 1];
            int right = mid >= n - 1 ? Integer.MIN_VALUE : nums[mid + 1];

            if (midEle > left && midEle > right)
                // if current number is larger then adjacent number ,we found a peak
                return mid;
            else if (left >= right)
                // left element is larger then right
                // means we are on down slope
                // to find peak we need to go on left side
                r = mid - 1;
            else
                // left element is smaller then right
                // means we are on up slope
                // to find peak we need to go on right side
                l = mid + 1;

        }
        return -1;
    }
}

/**
 * very BAD approach
 * this is how we shouldn't be doing
 * 
 * if(mid>0 && mid<n-1){
 * if(nums[mid]> nums[mid-1] && nums[mid] > nums[mid+1])return mid;
 * else if(nums[mid-1]>=nums[mid+1])r=mid-1;
 * else l=mid+1;
 * }else if(mid==0){
 * if(nums[mid+1]< nums[0]){
 * return 0;
 * }else{
 * l=mid+1;
 * }
 * }else{
 * if(nums[mid-1]<nums[n-1]){
 * return n-1;
 * }else{
 * r=mid-1;
 * }
 * }
 */
class Solution {
    public int maximumCount(int[] nums) {
        // since array is sorted we can find first positive integer's index and last
        // negative integer's index
        // since 0 do not count as positive or negative we have to find two indexes
        int posIndex = findFirstPositive(nums);
        int negIndex = findLastNegative(nums);
        if (posIndex == -1 && negIndex == -1)
            return 0; // if both of them are zero means array is full of 0s
        if (posIndex == -1) {
            return negIndex + 1;
            // if positive number do not exists
        } else if (negIndex == -1) {
            // if negative numbers number do not exists
            // total elements-first index of positive element + 1(first element will be
            // counted too)
            return (nums.length - 1) - posIndex + 1;
        }
        // find maximum between negative and positive numbers
        return Math.max(negIndex + 1, (nums.length - 1) - posIndex + 1);
    }

    int findFirstPositive(int[] arr) {
        int l = 0, r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] <= 0) {
                // if element is negative or 0 then we continue search on right half
                l = mid + 1;
            } else {
                // if element is positive then we store current index as possible ans and
                // continue search on left half
                ans = mid;
                r = mid - 1;
            }
        }
        return ans;
    }

    int findLastNegative(int[] arr) {
        int l = 0, r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= 0) {
                // if element is positive or 0 then we continue search on left half
                r = mid - 1;
            } else {
                // if element is negative then we store current index as possible ans and
                // continue search on right half
                ans = mid;
                l = mid + 1;
            }
        }
        return ans;
    }
}
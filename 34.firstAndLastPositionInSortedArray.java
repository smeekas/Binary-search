class Solution {
    public int[] searchRange(int[] nums, int target) {
        int ans[] = new int[2];
        ans[0] = firstIndex(nums, target);
        ans[1] = lastIndex(nums, target);
        return ans;
    }

    int firstIndex(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) {
                // if same then we might have more elements on left and we need to return a
                // first position of current element.
                // store current index as possible answer and search on left portion
                ans = mid;
                r = mid - 1;
            } else if (arr[mid] < target) {
                // if element is smaller then, we need to search on right portion
                l = mid + 1;
            } else {
                // if element is larger then, we need to search on left portion
                r = mid - 1;
            }
        }
        return ans; // return ans
    }

    int lastIndex(int arr[], int target) {
        int l = 0, r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) {
                // if same then we might have more elements on right and we need to return a
                // last position of current element
                // store current index as possible answer and search on right portion
                ans = mid;
                l = mid + 1;
            } else if (arr[mid] < target) {
                // if element is smaller then, we need to search on right portion
                l = mid + 1;
            } else {
                // if element is larger then, we need to search on left portion
                r = mid - 1;
            }
        }
        return ans; // return ans
    }
}
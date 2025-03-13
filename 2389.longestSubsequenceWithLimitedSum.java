import java.util.Arrays;

class Solution {
    public int[] answerQueries(int[] nums, int[] queries) {
        // we sort nums since order do not matter and we want subsequence
        Arrays.sort(nums);
        int n = nums.length;
        // we need to find summations
        // we can prefix sum nums array
        // for element i will contain sum of element from element-0 to element-i
        int[] pre = new int[n];
        pre[0] = nums[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + nums[i];
        }
        int m = queries.length;
        int ans[] = new int[m];
        for (int i = 0; i < m; i++) {
            // now we try to do binary search
            ans[i] = bs(pre, queries[i]);
        }
        return ans;

    }

    int bs(int[] arr, int num) {
        // we want to find largest number that is <=num
        int l = 0, r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == num) {
                // if current number matches with target, we store it as possible answer
                // we continue search on right half (since we want largest number <=target)
                ans = mid;
                l = mid + 1;
            } else if (arr[mid] < num) {
                // if current number is <= target, we store it as possible answer
                // we continue search on right half (since we want largest number <=target)
                ans = mid;
                l = mid + 1;
            } else {
                // if current number is > target then we need to limit our search space
                // we continue search on left half
                r = mid - 1;
            }
        }

        return ans == -1 ? 0 : ans + 1;
    }
}
import java.util.List;

class Solution {
    public int minLengthAfterRemovals(List<Integer> nums) {
        int len = nums.size();
        int midI = len / 2;
        int midEle = nums.get(midI);
        int left = upperBound(nums, midEle);
        int right = lowerBound(nums, midEle);
        int count = right - left + 1;
        if (count >= Math.ceil((float) len / 2)) {
            // (len-count) other elements
            // (len-count)*2 elements can be removed
            // len-count same elements where we found count
            // other len-count is other elements
            // total how many will remain?
            // len-(len-count)*2 =count*2-len
            return count * 2 - len;
        } else {
            // count is less than half
            // so basically element will cancel each other
            // if total elements are even, all elements will be cancelled
            // if total elements are odd, only one element will left in the array
            return len % 2;
        }
    }

    int upperBound(List<Integer> arr, int num) {
        int l = 0, r = arr.size() - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int curr = arr.get(mid);
            if (curr == num) {
                ans = mid;
                r = mid - 1;
            } else if (curr < num) {
                l = mid + 1;
            } else {
                // curr > num
                ans = mid;
                r = mid - 1;
            }
        }
        return ans;
    }

    int lowerBound(List<Integer> arr, int num) {
        int l = 0, r = arr.size() - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int curr = arr.get(mid);
            if (curr == num) {
                ans = mid;
                l = mid + 1;
            } else if (curr < num) {
                ans = mid;
                l = mid + 1;
            } else {
                // curr > num
                r = mid - 1;
            }
        }
        return ans;
    }
}

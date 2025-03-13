import java.util.Arrays;

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        // number of potion matters, order do not. so we can sort potions
        Arrays.sort(potions);
        int n = spells.length;
        int ans[] = new int[n];
        for (int i = 0; i < n; i++) {
            int ele = spells[i];
            // now for every spell ele, we can try to bo binary search on potion
            ans[i] = binarySearch(potions, ele, success);
        }
        return ans;
    }

    int binarySearch(int arr[], int mul, long success) {
        // here from arr we have to find how many numbers are >=success
        // every element of arr will be multiplied by mul
        // we can try to find first number which is >=success
        // if first number is >= success means from that number to end of an array every
        // number will be >=success since array is sorted
        int l = 0, r = arr.length - 1;
        int ans = -1;
        long mu = mul;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            long res = (long) arr[mid] * mu; // our number
            if (res == success) {
                // if current number is same as target, it is possible answer so we store it
                // and try to find even smaller number on left part
                ans = mid;
                r = mid - 1;
            } else if (res > success) {
                // if current number is > target, it is possible answer so we store it
                // and try to find even smaller number on left part
                ans = mid;
                r = mid - 1;
            } else {
                // if current number is < target, we try to fnd greater number on right half
                l = mid + 1;
            }
        }
        // if answer is still -1 means we cannot find number so return 0
        // else return total numbers (from current index to very end)
        return ans == -1 ? 0 : (arr.length - 1) - ans + 1;
    }
}
class Solution {
    public int mySqrt(int x) {
        if (x == 0)
            return x;
        // answer can lie between 1 & x
        int l = 1, r = x;
        int ans = 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            double mul = (double) mid * (double) mid;
            if (mul == x) {
                // if current number squared is equal to given number that means mid is sqrt
                ans = mid;
                return ans;
            } else if (mul > x) {
                // square is way to high
                r = mid - 1;
            } else {
                // mul < x
                // since we want to find nearest integer, we will store mid as possible answer
                // even though mul < x
                ans = mid;
                l = mid + 1;
            }
        }
        return ans;
    }
}
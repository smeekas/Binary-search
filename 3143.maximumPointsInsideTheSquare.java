import java.util.HashSet;

class Solution {
    public int maxPointsInsideSquare(int[][] points, String s) {

        int l = 0, r = 1000000000;
        int ans = 0;

        // this is below kind of binary search
        // RRRRRRRRRRRRWWWWWWWWWWWWWW
        // W-wrong, R-right
        // if square of size n is invalid then n+1,n+2, n+3 are invalid too
        while (l <= r) {
            int bound = l + (r - l) / 2;
            // for square size of {bound}, how many point it can have?
            int pointCount = get(bound, points, s);
            if (pointCount == -1) {
                // if current square size {bound} contains duplicate tag, means it can never we
                // answer.
                // search for smaller square size
                r = bound - 1;
            } else {
                // this square size contains possible answer!!!!
                ans = pointCount;
                // can we do better?
                // search in larger square
                l = bound + 1;
            }
        }
        return ans;
    }

    int get(int bound, int[][] points, String s) {
        int pointCount = 0;
        HashSet<Character> hm = new HashSet<>();
        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            // to match it with square-size, we don't need it's sign
            int x = Math.abs(point[0]);
            int y = Math.abs(point[1]);
            if (x > bound || y > bound)
                continue; // if current point is beyond limit of current square, ignore it.
            if (hm.contains(s.charAt(i))) {
                // if set contain the tag, then it is repeating return -1 as current square size
                // {bound} will have duplicate tag
                return -1;
            } else {
                hm.add(s.charAt(i));
                // save current tag
                // this point will be counted.
                pointCount++;
            }
        }
        return pointCount;

    }
}
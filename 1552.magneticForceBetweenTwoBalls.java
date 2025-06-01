import java.util.Arrays;

class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length;
        // same to same as question number:2517 . no difference
        long l = 1, r = position[n - 1] - position[0];
        long ans = 0;
        while (l <= r) {
            long force = l + (r - l) / 2;

            long balls = getBalls(position, force);

            if (balls == m) {
                ans = force;
                /**
                 * with {force} force we have m balls
                 * decrease the force will increase the balls
                 * increase the force will decrease the balls
                 */
                l = force + 1;
            } else if (balls > m) {
                ans = force;
                l = force + 1;
                // increasing force will cause balls to decrease
            } else {
                // balls < m
                r = force - 1;
            }
        }
        return (int) ans;
    }

    long getBalls(int[] pos, long force) {

        int curr = pos[0];
        long count = 1;
        for (int i = 1; i < pos.length; i++) {
            if (pos[i] - curr >= force) {
                count++;
                curr = pos[i];
            }
        }
        return count;
    }
}
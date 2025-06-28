import java.util.*;

class Solution {
    int LARGE = (int) (1e9);
    int MAX = Integer.MAX_VALUE;
    int xd[] = { 1, -1, 0, 0 };
    int yd[] = { 0, 0, 1, -1 };

    public int maximumMinutes(int[][] grid) {

        return bs(grid);

    }

    int bs(int[][] grid) {
        int l = 0, r = (int) 1e9;
        /**
         * here search space is RRRRRRRRRRRWWWWWWWWWWWWW
         * R- right
         * w- wrong
         * 
         * we can do binary search on wait time of person!
         */
        int[][] visitedFire = fireState(grid); // this is visited state of fire
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;

            // can person survive if he/she wait for {mid} minutes?
            boolean survived = canSurvive(grid, mid, visitedFire);

            if (survived) {
                // person can wait for {mid}
                // possible answer
                // can we do better?
                // search on right
                ans = mid;
                l = mid + 1;
            } else {
                // nah, person cannot survive {mid} minutes
                // search on left side
                r = mid - 1;
            }
        }
        return ans;
    }

    int[][] fireState(int[][] grid) {
        /**
         * normal BFS to get visited state of fire
         * multi-source bfs
         */
        int n = grid.length, m = grid[0].length;
        Queue<Pair> qFire = new ArrayDeque<>();
        int[][] visitedFire = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    visitedFire[i][j] = 0;
                    qFire.add(new Pair(i, j, 0));
                } else {
                    visitedFire[i][j] = MAX;
                }
            }
        }
        while (!qFire.isEmpty()) {
            Pair node = qFire.poll();
            for (int i = 0; i < xd.length; i++) {
                int nx = node.x + xd[i];
                int ny = node.y + yd[i];
                if (isValid(nx, ny, n, m) && grid[nx][ny] != 2) {
                    if (node.wt + 1 < visitedFire[nx][ny]) {
                        visitedFire[nx][ny] = node.wt + 1;
                        qFire.add(new Pair(nx, ny, node.wt + 1));
                    }
                }
            }
        }
        return visitedFire;
    }

    boolean canSurvive(int[][] grid, int waitTime, int[][] fire) {
        int n = grid.length, m = grid[0].length;

        int[][] visitedPerson = new int[n][m];
        Queue<Pair> qPerson = new ArrayDeque<>();

        for (int[] row : visitedPerson)
            Arrays.fill(row, MAX);

        // person will wait for {waitTime}
        // so initial value at [0][0] will be {waitTime}
        visitedPerson[0][0] = waitTime;
        qPerson.add(new Pair(0, 0, waitTime));

        while (!qPerson.isEmpty()) {
            Pair node = qPerson.poll();
            for (int i = 0; i < xd.length; i++) {
                int nx = node.x + xd[i];
                int ny = node.y + yd[i];
                if (isValid(nx, ny, n, m) && grid[nx][ny] != 2) {
                    if (nx == n - 1 && ny == m - 1) {
                        // WE JUST NEED THIS SPECIAL CASE HERE
                        // why?
                        // because if we don't then we will miss the case where fire and person are
                        // reaching at same time
                        // and generally we update the cell only if new time in [nx][ny] cell is <
                        // fire[nx][ny]. again < and not <=
                        // now that condition is different for last cell, as person can rich at
                        // same time as fire in last cell
                        // so we need to handle it differently
                        if (node.wt + 1 < visitedPerson[nx][ny] && node.wt + 1 <= fire[nx][ny]) {
                            // if new time is lesser and new time is same or lesser then fire then person is
                            // able to reach till end by waiting {waitTime} minutes
                            // return true
                            return true;
                        } else {
                            // person is cooked.
                            // return false (anyway this is last cell)
                            return false;
                        }
                    } else if (node.wt + 1 < visitedPerson[nx][ny] && node.wt + 1 < fire[nx][ny]) {
                        // normal condition
                        visitedPerson[nx][ny] = node.wt + 1;
                        qPerson.add(new Pair(nx, ny, node.wt + 1));
                    }
                }
            }
        }

        // after everything is finished, now we can assume that if last cell is MAX,
        // then person was not able to reach till end and he/she was intercepted by fire
        // in-between.
        // if last cell is not max mean he/she was able to reach successfully!
        return visitedPerson[n - 1][m - 1] != MAX;
    }

    boolean isValid(int x, int y, int n, int m) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

}

class Pair {
    int x, y, wt;

    public Pair(int x, int y, int wt) {
        this.x = x;
        this.y = y;
        this.wt = wt;
    }
}
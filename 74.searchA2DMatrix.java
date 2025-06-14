class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        return rowOrderBs(matrix, target);

        // below is approach where we first find loweBounded row and then search in that
        // row.
        // log(n)+log(m)
        // int row=lowerBound(matrix,target);
        // return lowerBoundOnRow(matrix[row],target);
    }

    int lowerBound(int[][] matrix, int target) {
        int n = matrix.length;
        int l = 0, r = n - 1;
        int ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int ele = matrix[mid][0];
            if (ele == target) {
                ans = mid;
                l = mid + 1;
            } else if (ele < target) {
                ans = mid;
                l = mid + 1;
            } else {
                // ele > target
                r = mid - 1;
            }
        }
        return ans;
    }

    boolean lowerBoundOnRow(int[] matrix, int target) {
        int n = matrix.length;
        int l = 0, r = n - 1;
        int ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int ele = matrix[mid];
            if (ele == target) {
                return true;
            } else if (ele < target) {
                l = mid + 1;
            } else {
                // ele > target
                r = mid - 1;
            }
        }
        return false;
    }

    boolean rowOrderBs(int[][] matrix, int target) {
        // log(n+m)
        // binary search on 2d matrix by treating matrix as 1d array
        int n = matrix.length;
        int m = matrix[0].length;
        int l = 0, r = n * m - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;

            int row = mid / m; // divide by row size to get row number
            int col = mid % m; // mod row size to get col index in row
            int ele = matrix[row][col];
            if (ele == target)
                return true;
            else if (ele > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }
}
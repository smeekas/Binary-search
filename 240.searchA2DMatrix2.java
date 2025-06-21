class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        /**
         * perspective
         * row is sorted and column is sorted
         * means for element ij,
         * in ith row all element on left (0 to j ) are smaller then ij
         * in jth column all element on down (i to n-1) are larger then ij
         * 
         * if we start from 0th row and nth column we can actually apply binary search
         * here!
         */
        int m = matrix[0].length;
        int n = matrix.length;
        int row = 0, col = m - 1;
        int count = n + m; // max binary search can go till here
        while (count-- > 0 && col >= 0 && row < n) {
            // column will decrease and row will increase.
            // why? because from where we are starting, we do right to left and top to
            // bottom

            if (matrix[row][col] == target)
                return true;
            else if (matrix[row][col] > target) {
                // element is larger then target
                // so in {col} column from {row to n bottom direction} it can never exists
                // we can ignore this column and move to prev column
                // prev column will have smaller elements
                col--;
            } else {
                // matrix[row][col] < target
                // element is smaller then target
                // so from in {row} row, {0 to col left side} it can never exists as those
                // element are even smaller then matrix[row][col]
                // so increase the row,
                // next row will have larger elements
                row++;
            }
        }
        return false;
    }
}
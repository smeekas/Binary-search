class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        // bruteforce can be to create array and find it
        // better approach will be to use heap

        int n = matrix.length;
        int l = matrix[0][0], r = matrix[n - 1][n - 1];
        /**
         * or we can do binary search!!!!
         * smallest number will be [0][0] and largest at [n-1][n-1]
         * 
         * for every number we can find how many small element it has on left side
         */
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            // int getK=getCount(matrix,mid);
            int getK = getCountEffi(matrix, mid);
            if (getK == k) {
                // current number [mid] has exactly K digit on left side.
                // can we do better? we want tightest upper bound
                // store current number as possible answer and search on left
                ans = mid;
                r = mid - 1;
            } else if (getK < k) {
                // we have < k element on left side of [mid].
                // search on right to increase K
                l = mid + 1;
            } else {
                // getK > k
                // we have more then K element
                // that means we have at-least K element on left
                // caaannnn be possible answer
                // search on left side to decrease K (we want answer close to K)
                ans = mid;
                r = mid - 1;
            }
        }
        return ans;
    }

    int getCount(int[][] matrix, int ele) {
        // slightly unoptimized

        int n = matrix.length;
        int count = 0;
        // O(n*logN)
        for (int i = 0; i < n; i++) {
            // every row is sorted
            // every column is sorted
            if (matrix[i][n - 1] <= ele) {
                // if last element in current row is smaller than ele, then all element in
                // current row are small
                // add entire row in count
                count += n;
            } else if (matrix[i][0] > ele) {
                // first element is larger than ele, means no other element in current row will
                // be smaller than ele.
                // nothing
            } else {
                // we will again do binary search on current row to find greater smaller number
                // than ele

                int l = 0, r = n - 1;
                int ans = 0;
                while (l <= r) {
                    int mid = l + (r - l) / 2;
                    int midEle = matrix[i][mid];
                    if (midEle == ele) {
                        // number match
                        // current number can be possible ans
                        // we want tight upper bound so search on right side
                        // there can be duplicates
                        ans = mid;
                        l = mid + 1;
                    } else if (midEle < ele) {
                        // current element is smaller than ele
                        // possible ans
                        // we can continue search on right
                        ans = mid;
                        l = mid + 1;
                    } else {
                        // midEle > ele
                        // search on left
                        r = mid - 1;
                    }
                }
                // element from 0 to ans (total ans+1) element are smaller then ele in current
                // row
                count += (ans + 1);
            }
        }
        return count;
    }

    int getCountEffi(int[][] matrix, int ele) {

        int n = matrix.length;
        int r = 0, c = n - 1;
        int maxIt = n + n;
        int count = 0;
        // approach like search 2d matrix 2 won't work here
        // because, see below test case
        // 1 2
        // 1 3
        // for 1 we will stop at 0th row only which is wrong because in same column
        // duplicate exists
        // search work but counting do not

        // very similar approach below
        /**
         * we loop through every row.
         * we have single j pointer for column which start at very end
         * for every row we decrease column point if element is larger
         * once that finishes, all element on left will be smaller which we can add to
         * our count
         * after that in next row we will start from same decreased column only. why?
         * matrix is sorted row wise and column wise.
         * so if in current row r, if element at column c, is larger matrix[r][c]>ele,
         * then
         * in next row, matrix[r+1][c] will be matrix[r+1][c] > ele
         * so no point in searching on right part again
         */
        // O(n)
        // while loop is amortized O(n)
        for (int i = 0; i < n; i++) {
            // for every row
            while (c >= 0 && matrix[i][c] > ele)
                c--;
            count += (c + 1);
        }

        return count;
    }
}

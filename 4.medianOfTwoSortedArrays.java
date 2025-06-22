class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (true)
            return bs(nums1, nums2);

        /**
         * O(n+m) time
         * O(1) space
         */
        int n = nums1.length;
        int m = nums2.length;
        int nm = n + m;
        /**
         * if final length will be odd then the middle (nm/2)th element is the one we
         * are looking for
         * if final length will be even then the middle two element we need to find
         */
        int index1 = nm % 2 == 1 ? nm / 2 : nm / 2 - 1;
        int index2 = nm % 2 == 1 ? nm / 2 : nm / 2;
        int i = 0, j = 0, k = 0;

        int first = -1;
        int second = -1;
        while (i < n && j < m) {
            if (nums1[i] < nums2[j]) {
                if (k == index1)
                    first = nums1[i];
                if (k == index2)
                    second = nums1[i];
                i++;
            } else {
                if (k == index1)
                    first = nums2[j];
                if (k == index2)
                    second = nums2[j];
                j++;
            }
            k++;
        }
        while (i < n) {
            if (k == index1)
                first = nums1[i];
            if (k == index2)
                second = nums1[i];
            i++;
            k++;
        }
        while (j < m) {
            if (k == index1)
                first = nums2[j];
            if (k == index2)
                second = nums2[j];
            j++;
            k++;
        }
        return (double) (first + second) / (double) 2;
    }

    double bs(int[] nums1, int[] nums2) {
        // what is the idea?
        // tushar roy
        // idea is first we estimate median in merged array that how many numbers will
        // be on left and how many on right
        // then we do binary search on first array.
        // if median is M elements and mid is m then in merged array from second array
        // we need M-m elements on left side
        int n = nums1.length;
        int m = nums2.length;
        int median = (n + m + 1) / 2;
        // we always want max number on left in case of odd lengthed array
        // for ex. 0 1 2 3 4 | 012 and 34
        // here median is number of elements
        // for length 5 => 3 number of left
        // for length 6 => 3 number of left
        if (n > m)
            return bs(nums2, nums1); // we want to binary search on smaller array
        int l = 0, r = n; // here we want to binary search on number of elements
        // for ex. 0 1 5 7 9
        // -------|-|-|-|-|-|
        // | represent we will take that all left numbers from |
        // for length=5 we bs on 0 to 5 total 6 possibilities

        while (l <= r) {
            int mid = l + (r - l) / 2; // if we take mid elements from first array....
            int mid2 = median - mid; // then we need median-mid elements from second array on left side

            /**
             * right most element of array1's left part will be smaller then left most
             * element of right side
             * 
             * same will hold true for array2
             * 
             * but we have divided both arrays in left and right part
             * 
             * so we need to check if array1's left part's right most element is smaller
             * then array2's right part's left most element
             * 
             * same for array2's left part and array1's right part
             * 
             */

            /**
             * from | example
             * if we have to take right most element on left side of cut, then we need mid-1
             * for ex. 0 1 5 7 9
             * -------|-|-|-|-|-|
             * if mid is 3 then element would be (mid-1)th index=5
             * left most element of right part would be (mid)th element =7
             */
            int l1 = mid == 0 ? Integer.MIN_VALUE : nums1[mid - 1];
            int r1 = mid >= n ? Integer.MAX_VALUE : nums1[mid];

            int l2 = mid2 == 0 ? Integer.MIN_VALUE : nums2[mid2 - 1];
            int r2 = mid2 >= m ? Integer.MAX_VALUE : nums2[mid2];

            if (l1 <= r2 && l2 <= r1) {
                // cut is perfect
                // our property is holding true
                if ((n + m) % 2 == 0) {
                    // if array is even lengthed then we need middle two element and average it out
                    // we will take right most element from both left part of array-1 & array-2
                    // we will take left most element from both right part of array-1 & array-2
                    // this will give us middle two element if we have merged the array
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else {
                    // if array is odd lengthed then we will take middle element
                    // since we have kept max number of element on left side for ex. 012 and 45
                    // we can just take right most element from both array's left part
                    return Math.max(l1, l2);
                }
            } else if (l1 < r2) {
                // means l2 <= r1 is false and l2 > r1 this condition is holding true
                // we want to increase r1 for that we need to move right side of array 1
                l = mid + 1;
            } else {
                // means l1 <= r2 is false and l1 > r2 is holding true
                // we want to decrease l1. for that we need to move left side of array 1
                r = mid - 1;
            }
        }
        return -1;
    }
}

import java.util.ArrayList;
import java.util.HashMap;

class RangeFreqQuery {
    /*
     * idea is for every number we will store it's index in array.
     * as we go we will be storing indexes so for every number all it's indexes will
     * be in sorted order.
     * now for every query we can find upperBound of left index and lowerBound of
     * right index & get number of elements between left & right index by
     * subtracting left index from right index.
     * 
     */
    HashMap<Integer, ArrayList<Integer>> hm;

    public RangeFreqQuery(int[] arr) {
        hm = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            hm.putIfAbsent(arr[i], new ArrayList<>());
            hm.get(arr[i]).add(i);
        }
    }

    public int query(int left, int right, int value) {
        if (!hm.containsKey(value))
            return 0; // whatever value given to find do not exists
        ArrayList<Integer> indexes = hm.get(value);

        /**
         * why upperBound for left index?
         * it might happen that left index do not exists in array of index
         * so if it do not exists in we have to find next index, that will be next index
         * greater than given left index and index that exists in array of index
         * 
         * 
         * why lowerBound for right index?
         * it might happen that right index do not exists in array of index
         * so if it do not exists in we have to find previous index, that will be
         * previous index lesser than given right index and index that exists in array
         * of index
         */
        int upperOfLeft = upperBound(indexes, left);
        int lowerOfRight = lowerBound(indexes, right);
        if (upperOfLeft == -1 || lowerOfRight == -1) {
            // if upper bound of any number do not exists means number that was given to us
            // for finding upperBound, is outside of our array(outside of right-side of
            // array). (if it existed then we would have find upperBound )
            // if lower bound of any number do not exists means number that was given to us
            // for finding lowerBound, is outside of our array(outside of left-side of
            // array). (if it existed then we would have find lowerBound)
            return 0;
        }
        // (right-left+1)
        return lowerOfRight - upperOfLeft + 1;
    }

    int upperBound(ArrayList<Integer> arr, int val) {
        int n = arr.size();
        int l = 0, r = n - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int currEle = arr.get(mid);
            if (currEle == val) {
                // here currEle==val
                // possible than on left side we have elements that are ==val
                // on left side of curr element elements will be <val OR ==val
                // also since we want to find upper bound but if we have similar elements than
                // we want to maximize numbers which we take, so we expand try to search on
                // left half
                // so we search on left side
                ans = mid;
                r = mid - 1;
            } else if (currEle < val) {
                // we need to try better to find val on right side.
                l = mid + 1;
            } else {
                // currEle > val
                // greater element than what we need to find
                // can be possible ans
                // store ans and find smaller element but greater than val on left side
                ans = mid;
                r = mid - 1;
            }
        }
        return ans;
    }

    int lowerBound(ArrayList<Integer> arr, int val) {
        int n = arr.size();
        int l = 0, r = n - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int currEle = arr.get(mid);
            if (currEle == val) {
                // one of the possible answer
                // we need to find extreme lowerBound so we try to search on right half
                // might be chance that we have more vals on right half
                // similar to upperBound
                ans = mid;
                l = mid + 1;
            } else if (currEle < val) {
                // curr element is smaller than val.
                // possible answer.
                // we still search on right half
                ans = mid;
                l = mid + 1;
            } else {
                // curr element is greater than val
                // we need to limit our search space on left side to find answer
                // we search on left half
                r = mid - 1;
            }
        }
        return ans;
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */
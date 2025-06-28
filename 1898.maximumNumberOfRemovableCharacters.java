class Solution {
    public int maximumRemovals(String s, String p, int[] removable) {
        int n = removable.length;
        int l = 0, r = n - 1;
        int ans = -1;
        /**
         * bruteforce will be to try indexes from removable one by one
         * 
         * OR
         * binary search on removable!!
         */
        /**
         * PATTERN RRRRRRRRRWWWWWWWWWWWW
         * R- right
         * W- wrong
         */
        while (l <= r) {
            int mid = l + (r - l) / 2;
            boolean isPossible = isSubSequence(removable, mid, s, p);
            if (isPossible) {
                // it is possible to create subsequences
                // possible answer
                // but we want to maximize it
                // so search on right side
                ans = mid;
                l = mid + 1;
            } else {
                // not possible
                // search on left side
                r = mid - 1;
            }
        }
        return ans + 1;
    }

    boolean isSubSequence(int arr[], int limit, String s, String p) {

        /**
         * approach with set
         * HashSet<Integer> hs=new HashSet<>();
         * for(int i=0;i<=limit;i++)hs.add(arr[i]);
         * 
         * add removable characters till limit from removable array into set
         * 
         * this set will help us when we iterate through s
         */

        /**
         * another approach will be to kind of erase characters from original array so
         * that we do not match it
         */
        int pLen = p.length();
        int sLen = s.length();
        char sCharArr[] = s.toCharArray();
        for (int i = 0; i <= limit; i++)
            sCharArr[arr[i]] = '/';

        int pIn = 0; // variable to match p in s
        for (int i = 0; i < sLen; i++) {
            // if(hs.contains(i))continue;
            if (sCharArr[i] == p.charAt(pIn)) {
                // if character matches then increase pIn (we will try to match next character
                // now)
                pIn++;
                if (pIn == pLen)
                    // if p is over means we found p (subsequence) in s!
                    return true;
            }
        }
        // even after iterating through entire s we didn't found any p (subsequence)
        return false;
    }
}
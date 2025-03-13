import java.util.Arrays;

class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {

        // array that hold smallest character's frequency for every word
        int[] wordLen = new int[words.length];
        int i = 0;
        for (String word : words) {
            wordLen[i++] = getLength(word);
        }
        // order do not matter, count matters. so we can sort wordLen array
        Arrays.sort(wordLen);
        int qLen = queries.length;
        int ans[] = new int[qLen];
        for (i = 0; i < qLen; i++) {
            // we do binary search on wordLen array for every query's word length
            ans[i] = binarySearch(wordLen, getLength(queries[i]));
        }
        return ans;
    }

    int getLength(String word) {

        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }
        for (int num : freq)
            if (num != 0)
                return num;
        return 0;
    }

    int binarySearch(int[] arr, int num) {
        // find elements in arr who's value are higher than num
        // if num is equal then... since equal are not allowed we search on right. to
        // increase arr element's value
        // if num < arr element then... one of the ans. we search on left.
        // if num > arr element then... search on right
        int l = 0, r = arr.length - 1;

        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (num == arr[mid]) {
                l = mid + 1;
            } else if (num < arr[mid]) {
                // array item has higher value than num.
                // one of possible answer
                ans = mid;
                r = mid - 1;
            } else {

                l = mid + 1;
            }
        }

        return ans == -1 ? 0 : (arr.length - 1) - ans + 1;
    }
}
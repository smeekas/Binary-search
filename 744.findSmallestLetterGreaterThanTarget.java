class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0, r = letters.length - 1;
        char ans = letters[0]; // default ans. first element
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (letters[mid] == target) {
                // if letter matches than we continue search on right half as we need to find
                // letter greater than target
                l = mid + 1;
            } else if (letters[mid] < target) {
                // if current letter is smaller than target then we continue search on right
                // half
                l = mid + 1;
            } else {
                // if current letter is greater than target then we store current letter as
                // possible ans
                // but we also need to find smallest greater element for ex. target is "f", we
                // found "z" it does't mean that "z" is answer. we continue search on left half
                // to find maybe "h", "g" etc...
                ans = letters[mid];
                r = mid - 1;
            }
        }
        return ans;
    }
}
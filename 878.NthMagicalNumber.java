class Solution {
    public int nthMagicalNumber(int n, int a, int b) {
        // bruteforce will be to get all number and get nth one.
        // optimal
        /*
         * Binary Search!
         * minimum number can be min of a and b. why?
         * because number < min(a,b) won't be divisible by min(a,b)
         * 
         * maximum can be min(a,b)*n why?
         * we want nth number divisible by a or b.
         * so if we can get min, min(a,b) then get nth number assuming that number is
         * divisible by only min(a,b)
         * 
         * that will be upper limit of our search
         */
        long l = Math.min(a, b), r = n * l;
        long ans = -1;
        long lcmAb = lcm(a, b);
        while (l <= r) {
            long mid = l + (r - l) / 2;
            long count = getNum(mid, a, b, lcmAb);
            // get position of {mid}
            if (count == n) {
                // position is same
                // possible answer
                // we want tightest bound
                // continue search on left because we MAY have smaller number whose position is
                // nth. if it exists then it won't be far away
                // standard template
                ans = mid;
                r = mid - 1;
            } else if (count > n) {
                // count is > n means it have n number on left side
                // can be possible answer.
                // search on left side
                // since we want number to be close to n
                ans = mid;
                r = mid - 1;
            } else {
                // count < n
                // increase our assumption
                // search on right
                l = mid + 1;
            }
        }
        long mod = (long) 1e9 + 7;
        return (int) (ans % mod);
    }

    long getNum(long val, long a, long b, long lcmAb) {
        // val/a => how many number on left divisible by a ex. 16/4=>4 => 4,8,12,16
        // val/b => how many number on left divisible by b
        /*
         * just val/a + val/b works if gcd(a,b) is 1 ex. 3,7 OR 2,11 etc...
         * because from 1 to val, there won't be any common number that is divisible by
         * both a and b
         * 
         * what if there is common?
         * 
         * for that we also take how many number are on left divisible by lcm(a,b) =>
         * least common multiple. lcm will occur in both val/a and val/b
         * 
         * think like, some number (divisible by both a and b) are counted twice in
         * val/a and val/b, so we need to remove count 1 time.
         * 
         * ex. a=3 b=4 n=5
         * 3 4 6 8 9 12 15 16 18 20 21
         * if number is 13
         * 13/3 => 4
         * 13/4 => 3
         * result=>7, actual=>6
         * extra included number=12
         * lcm(3,4)=>12
         * so we remove 13/12=>1
         * 
         * 
         * if number do not have common then Least common multiple will be a*b
         * if val < a*b then val/a*b will subtract 0. which is perfect
         * 
         * if val > a*b then val/a*b will be some number which we will subtract (now a*b
         * is working just like if we have some lcm)
         * 
         */
        return val / a + val / b - val / lcmAb;
    }

    long gcd(long a, long b) {
        // greatest common divisor
        // we start loop from min(a,b) because we want greatest and number > min(a,b)
        // won't divide min(a,b)
        for (long i = Math.min(a, b); i >= 1; i--) {
            if (a % i == 0 && b % i == 0)
                return i;
        }
        return a * b;
    }

    long gcdEff(long a, long b) {
        // euclid algo
        while (a != b) {

            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;

    }

    long lcm(long a, long b) {
        // lcm*gcd=a*b;
        return a * b / gcd(a, b);
    }
}
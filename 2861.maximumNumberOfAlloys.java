import java.util.List;

class Solution {
    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock,
            List<Integer> cost) {
        int l = 1, r = 1000000000;
        /**
         * we can try from l to r in linear manner
         * 
         * for every number of alloy, we can try to get how much budget that require
         * 
         * bruteforce O(m*K*n)
         * binarySearch O(logM*K*n)
         * k=> composition array size
         * n => each composition's size
         */
        int ans = 0;
        while (l <= r) {
            int canThisManyCreated = l + (r - l) / 2;

            int costToCreateGivenAlloy = getCost(n, k, canThisManyCreated, composition, stock, cost);

            if (costToCreateGivenAlloy == budget) {
                // match!
                // possible answer
                // can we do better?
                // increase number of alloy count
                ans = canThisManyCreated;
                l = canThisManyCreated + 1;
            } else if (costToCreateGivenAlloy < budget) {
                // we can create {canThisManyCreated} alloys, with lesser cost!
                // but we are allowed to spend {budget} budget
                // so store {canThisManyCreated} as possible answer
                // and try to create more alloy
                ans = canThisManyCreated;
                l = canThisManyCreated + 1;
            } else {
                // costToCreateGivenAlloy > budget
                // we are exceed the budget
                // reduce number of alloys. it will decrease budget too
                r = canThisManyCreated - 1;
            }
        }
        return ans;
    }

    int getCost(int n, int k, int howManyAlloy, List<List<Integer>> composition, List<Integer> stock,
            List<Integer> cost) {
        double newCost = Double.MAX_VALUE;
        for (List<Integer> compo : composition) {
            // we can choose any one composition
            double total = 0;
            int i = 0;
            // for each composition, we will try each metal
            // if they are in stock then good else we will buy them at given cost
            for (i = 0; i < n; i++) {
                double totalNumberOfMetal = (double) compo.get(i) * (double) howManyAlloy;

                if (totalNumberOfMetal <= (double) stock.get(i)) {
                    continue;
                } else {
                    double moreNeeded = totalNumberOfMetal - stock.get(i);
                    total += (double) cost.get(i) * moreNeeded;
                }

            }

            if (total < newCost) {
                // if new total is lesser then we will update
                // we want as low cost as we can
                newCost = total;
            }

        }
        return (int) newCost;
    }
}
import java.util.HashMap;

class TopVotedCandidate {
    HashMap<Integer, Integer> hm;
    int lead[], times[];

    public TopVotedCandidate(int[] persons, int[] times) {
        /**
         * what we do is for every timestamp we store top voted candidate.
         * then for query we try to find lowerBound of given time
         */
        int n = persons.length;
        this.times = times;
        lead = new int[n];
        hm = new HashMap<>();

        int soFarCount = Integer.MIN_VALUE;
        int soFarPerson = 0;

        for (int i = 0; i < n; i++) {

            int person = persons[i];
            if (hm.containsKey(person)) {
                hm.put(person, hm.get(person) + 1);
            } else {
                hm.put(person, 1);
            }

            int currPersonsCount = hm.get(person);

            if (currPersonsCount >= soFarCount) {
                // if current person's vote count is >= maximum vote of any other person so far\
                // update count and person
                soFarCount = currPersonsCount;
                soFarPerson = person;
            }
            // store current person in array for given index
            lead[i] = soFarPerson;
        }

    }

    public int q(int t) {
        int bestLow = -1;
        int l = 0, r = this.lead.length - 1;

        // lowerBound
        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (this.times[mid] == t) {
                bestLow = mid;
                l = mid + 1;
            } else if (this.times[mid] < t) {
                bestLow = mid;
                l = mid + 1;
            } else {
                // mid> t
                r = mid - 1;
            }
        }
        if (bestLow == -1) {
            return -1;
        }

        return this.lead[bestLow];
    }

}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
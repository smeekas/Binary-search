import java.util.ArrayList;
import java.util.HashMap;

class SnapshotArray {
    /**
     * bruteforce will be that for every snap we will store entire copy of an array
     * 
     * optimal solution is......
     * we will store indexes as key
     * snapId will be always in increasing order
     * for every key(index) we will store current snapId and actual value
     * to get any value of any index at given snapId,
     * from given index and snapId, we will try to find lowerBound of given snapId
     * because for stored snapIds ex. 1 4 7 8 for snapId 6, value will be same as
     * value is at snapId 4. because we are not duplicating entire array
     */
    HashMap<Integer, ArrayList<Snap>> hm;
    int size = -1;
    int snapId = 0;

    public SnapshotArray(int length) {
        hm = new HashMap<>();
        size = length;

    }

    public void set(int index, int val) {
        // if given index is higher than given size then we will not store it
        if (index >= size)
            return;
        if (!hm.containsKey(index)) {
            // if entry do not exists in hashMap then add arraylist
            hm.put(index, new ArrayList<>());
        }
        // add new Snap instance with current snapId
        hm.get(index).add(new Snap(snapId, val));
    }

    public int snap() {

        snapId++; // increment snapId
        return snapId - 1; // return previous snapId
    }

    public int get(int index, int snap_id) {
        if (!hm.containsKey(index)) // if entry do not exists then simply return it
            return 0;
        ArrayList<Snap> ind = hm.get(index); // get array
        int l = 0, r = ind.size() - 1;
        int ans = -1;
        // here we will try to find lowerBound
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midSnapId = ind.get(mid).snap;
            if (midSnapId == snap_id) {
                // same snapId?
                // possible answer
                // there can be multiple elements with same snapId
                // we are interested in extreme right lowerBound
                ans = mid;
                l = mid + 1;
            } else if (midSnapId < snap_id) {
                // curr snapId is lower than target
                // possible answer
                // we try to find better answer on right half
                ans = mid;
                l = mid + 1;
            } else {
                // current snapId is greater than target
                // so we try to search on left half
                r = mid - 1;
            }
        }
        if (ans == -1) // if no answer then return 0
            return 0;
        return ind.get(ans).val;
    }
}

class Snap {
    int snap, val;

    Snap(int snap, int val) {
        this.snap = snap;
        this.val = val;
    }
}
/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */
/*
 * 3
 * 0=> 0,15
 * 
 */
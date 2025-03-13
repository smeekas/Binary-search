import java.util.*;

class TimeMap {

    HashMap<String, ArrayList<Pair>> hm;

    public TimeMap() {
        hm = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!hm.containsKey(key)) {
            hm.put(key, new ArrayList<>());
        }

        hm.get(key).add(new Pair(timestamp, value));
    }

    public String get(String key, int timestamp) {

        if (hm.containsKey(key)) {
            ArrayList<Pair> arr = hm.get(key);
            int l = 0, r = arr.size() - 1;
            int ans = 0;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                int ele = arr.get(mid).timestamp;
                // lower bound of timestamp
                // if timestamp do not exists then we look for lowerBound of timestamp
                if (ele == timestamp) {
                    ans = mid;
                    l = mid + 1;
                } else if (ele < timestamp) {
                    ans = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }

            int prev_timestamp = arr.get(ans).timestamp;
            if (prev_timestamp <= timestamp) {

                return arr.get(ans).val;

            }
            return "";

        }
        return "";
    }
}

class Pair {
    int timestamp;
    String val;

    Pair(int timestamp, String val) {
        this.timestamp = timestamp;
        this.val = val;
    }
}
/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
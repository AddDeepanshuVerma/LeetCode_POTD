package BinarySearch;

import java.util.*;

class AvoidFlood_1488_2 {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        TreeSet<Integer> zeros = new TreeSet<>(); // keeps zeros occurance indexes in order
        HashMap<Integer, Integer> map = new HashMap<>();

        int[] ans = new int[n];
        for (int i = 0; i < rains.length; i++) {
            int rain = rains[i];
            if (rain == 0) {
                zeros.add(i);
                ans[i] = 1;
            } else {
                if (map.containsKey(rain)) {
                    int prevIdx = map.get(rain);
                    Integer idx = zeros.higher(prevIdx);
                    if (idx != null) {
                        zeros.remove(idx);
                        ans[idx] = rain;
                    } else return new int[]{};
                }
                map.put(rain, i);
                ans[i] = -1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        var obj = new AvoidFlood_1488_2();
        int[] ans = obj.avoidFlood(new int[]{1, 0, 2, 0, 3, 0, 2, 0, 0, 0, 1, 2, 3});
        System.out.println("ans = " + Arrays.toString(ans));
    }
}
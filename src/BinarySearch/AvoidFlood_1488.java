package BinarySearch;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;

class AvoidFlood_1488 {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        Deque<Integer> stack = new ArrayDeque<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        int[] ans = new int[n];
        for (int i = 0; i < rains.length; i++) {
            int rain = rains[i];
            if (rain == 0) {
                stack.addFirst(i);
                ans[i] = 1;
            } else {
                if (map.containsKey(rain)) {
                    int prevIdx = map.get(rain);
                    if (!stack.isEmpty() && prevIdx < stack.peekFirst()) {
                        int index = stack.removeFirst();
                        ans[index] = rain;
                    } else {
                        return new int[]{};
                    }
                } else {
                    map.put(rain, i);
                }
                ans[i] = -1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        var obj = new AvoidFlood_1488();
        int[] ans = obj.avoidFlood(new int[]{1, 0, 2, 0, 2, 1});
        System.out.println("ans = " + Arrays.toString(ans));
    }
}
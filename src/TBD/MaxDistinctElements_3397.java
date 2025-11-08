package TBD;

import java.util.HashMap;
import java.util.Map;

class MaxDistinctElements_3397 {
    public int maxDistinctElements(int[] nums, int k) {
        int range = (k << 1) + 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.merge(num, 1, Integer::sum);

        int count = 0;
        int inRange = 0;
        for (var item : map.entrySet()) {
            Integer val = item.getKey();
            Integer freq = item.getValue();
            if (val <= k && freq >= k) inRange++;
            count += Math.min(freq, range);
        }

        return count - inRange;
    }

    public static void main(String[] args) {
        var obj = new MaxDistinctElements_3397();
        int[] nums = {1, 1, 1, 2, 2, 2, 4, 4, 4, 4};
        int ans = obj.maxDistinctElements(nums, 3);
        System.out.println("ans = " + ans);
    }
}
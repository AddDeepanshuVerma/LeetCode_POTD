package array;

import java.util.HashMap;
import java.util.List;

class MaxIncreasingSubarrays_3350 {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        HashMap<Integer, Integer> map = new HashMap<>(); // {starting index, maxLength}

        for (int i = 0, j = 0; j < nums.size(); j++) {
            if (j > 0 && (nums.get(j) <= nums.get(j - 1))) {
                i = j;
            }
            int len = j - i + 1;
            map.merge(i, len, Math::max);
        }
        System.out.println("map = " + map);

        int max = 0;
        for (var item : map.entrySet()) {
            Integer index = item.getKey();
            Integer length = item.getValue();
            if (map.containsKey(index + length)) {
                int minAdjacentLength = Math.min(length, map.get(index + length));
                max = Math.max(max, minAdjacentLength);
            }
            max = Math.max(max, length >> 1); // if we divide this single subarray int two adjacent subArrays
        }

        return max;
    }

    public static void main(String[] args) {
        var obj = new MaxIncreasingSubarrays_3350();
        int ans = obj.maxIncreasingSubarrays(List.of(2, 5, 7, 8, 9, 2, 3, 4, 3, 1));
        System.out.println("ans = " + ans);

        // HashMap<Integer, Integer> map = new HashMap<>();
        // map.put(1, 5);
        // map.merge(1, 10, Math::max);
        // map.merge(1, 10, (a, b) -> Math.max(a, b));
        // System.out.println("map = " + map);
    }
}
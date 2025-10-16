package math;

import java.util.*;

class FindSmallestInteger_2598_2 {
    public int findSmallestInteger(int[] nums, int value) {
        Map<Integer, Integer> map = new HashMap<>(); // {reminder, count}
        for (int num : nums) {
            int rem = ((num % value) + value) % value;
            map.merge(rem, 1, Integer::sum);
        }
        int minValue = Integer.MAX_VALUE;

        for (int rem = 0; rem < value; rem++) {
            Integer freq = map.getOrDefault(rem, 0);
            minValue = Math.min(minValue, (value * freq) + rem);
        }
        return minValue;
    }

    public int findSmallestInteger2(int[] nums, int value) {
        int[] freq = new int[value];
        for (int num : nums) {
            int rem = ((num % value) + value) % value;
            freq[rem]++;
        }

        int minValue = Integer.MAX_VALUE;

        for (int rem = 0; rem < value; rem++) {
            int count = freq[rem];
            minValue = Math.min(minValue, (value * count) + rem);
        }
        return minValue;
    }

    public static void main(String[] args) {
        var obj = new FindSmallestInteger_2598_2();
        int[] nums = {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1};
        int ans = obj.findSmallestInteger(nums, 2);
        System.out.println("ans = " + ans);
    }
}
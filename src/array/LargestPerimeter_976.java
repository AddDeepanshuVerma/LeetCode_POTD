package array;

import java.util.Arrays;

class LargestPerimeter_976 {
    public int largestPerimeter(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);
        for (int i = nums.length - 3; i >= 0; i--) {
            int c = nums[i + 2];
            int b = nums[i + 1];
            int a = nums[i];
            if (a + b > c) {
                return a + b + c;
            }
        }

        return ans;
    }
}
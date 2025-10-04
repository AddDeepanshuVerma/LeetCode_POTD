package twoPointer;

class MaxArea_11 {
    public int maxArea(int[] height) {
        int start = 0, end = height.length - 1;

        int maxWater = 0;

        while (start < end) {
            int length = end - start;
            int left = height[start], right = height[end];

            maxWater = Math.max(maxWater, length * Math.min(left, right));

            if (left < right) start++;
            else end--;

        }

        return maxWater;
    }
}
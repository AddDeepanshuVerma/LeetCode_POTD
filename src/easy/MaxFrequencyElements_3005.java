package easy;

class MaxFrequencyElements_3005 {
    // one pass algo
    public static int maxFrequencyElements(int[] nums) {
        int[] freq = new int[101];
        int max = 0, count = 0;

        int maxValue = Integer.MAX_VALUE;

        for (int num : nums) {
            int temp = ++freq[num];
            if (temp > max) {
                max = temp;
                count = temp;
            } else if (temp == max) {
                count += temp;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 1, 4};
        int count = maxFrequencyElements(nums);
        System.out.println("count = " + count);
    }

}
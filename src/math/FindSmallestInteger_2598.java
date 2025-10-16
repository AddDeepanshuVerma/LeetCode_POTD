package math;

import java.util.HashSet;
import java.util.Set;

// bruteForce approach : TLE
class FindSmallestInteger_2598 {
    public int findSmallestInteger(int[] nums, int value) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            int rem = ((num % value) + value) % value;
            while(set.contains(rem)) {
                rem += value;
            }
            set.add(rem);
        }
        System.out.println(set);

        int i = 0;
        while(i <= nums.length){
            if(!set.contains(i)){
                return i;
            }
            i++;
        }

        return -1;
    }

    public static void main(String[] args) {
        var obj = new FindSmallestInteger_2598();
        int[] nums = {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1};
        int ans = obj.findSmallestInteger(nums, 2);
        System.out.println("ans = " + ans);
    }
}
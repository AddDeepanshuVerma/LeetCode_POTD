package queue;

import java.util.ArrayList;
import java.util.List;

class replaceNonCoprimes_2197_3 {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> res = new ArrayList<>();

        for (int num : nums) {
            res.add(num);
            while (res.size() >= 2) {
                int a = res.get(res.size() - 1);
                int b = res.get(res.size() - 2);
                int gcd = gcd(a, b);
                if (gcd == 1) break;
                res.removeLast();
                res.removeLast();
                res.add((int) ((long) a * b) / gcd);
            }
        }

        return res;
    }

    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    public int lcm(int a, int b) {
        return (int) ((long) a * b / gcd(a, b));
    }

    public static void main(String[] args) {
        int[] nums = {2009, 899, 23, 20677};
        replaceNonCoprimes_2197_3 obj = new replaceNonCoprimes_2197_3();
        System.out.println(obj.replaceNonCoprimes(nums));
    }
}
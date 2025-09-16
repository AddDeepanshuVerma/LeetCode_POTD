package queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

class replaceNonCoprimes_2197_2 {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int num : nums) {
            int current = num;
            while (!stack.isEmpty() && gcd(stack.peek(), current) > 1) {
                current = lcm(stack.pop(), current);
            }
            stack.push(current);
        }

        return new ArrayList<>(stack.reversed()); // .reversed was introduces in java 21
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
        replaceNonCoprimes_2197_2 obj = new replaceNonCoprimes_2197_2();
        System.out.println(obj.replaceNonCoprimes(nums));
    }
}
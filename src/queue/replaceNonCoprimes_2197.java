package queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

class replaceNonCoprimes_2197 {
	public List<Integer> replaceNonCoprimes(int[] nums) {
		ArrayDeque<Integer> stack = new ArrayDeque<>();

		for (int num : nums) {
			if (stack.isEmpty()) {
				stack.push(num);
			} else {
				while (!stack.isEmpty() && gcd(stack.peek(), num) > 1) {
					num = lcm(stack.pop(), num);
				}
				stack.push(num);
			}
		}

		List<Integer> res = new ArrayList<Integer>();
		while(!stack.isEmpty()) res.add(stack.removeLast());

		return res;
	}

	public int gcd(int a, int b) {
		while(b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}

		return a;
	}

	public int lcm(int a, int b) {
		long multiply = (long) a * b;
		return (int) (multiply / gcd(a, b));
	}


    public static void main(String[] args) {
        int[] nums = {2009, 899, 23, 23, 20677};
        replaceNonCoprimes_2197 obj = new replaceNonCoprimes_2197();
        System.out.println(obj.replaceNonCoprimes(nums));
        System.out.println(obj.gcd(287, 41));
    }

}
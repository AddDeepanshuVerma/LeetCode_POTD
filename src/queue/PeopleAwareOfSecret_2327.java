package queue;

import java.util.ArrayDeque;

class PeopleAwareOfSecret_2327 {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        final int MOD = (int) (1e9 + 7);

        ArrayDeque<int[]> dq = new ArrayDeque<>(); // {day, how many knew secret at the day}
        dq.offer(new int[]{1, 1});

        for (int i = 2; i <= n; i++) {
            // first pop all those who need to forget secret for day == i
            int expire = i - forget;
            while (!dq.isEmpty() && dq.peekFirst()[0] <= expire) {
                dq.removeFirst();
            }
            // now cont those who can share the secret
            int count = 0;
            ArrayDeque<int[]> temp = new ArrayDeque<>();
            while (!dq.isEmpty() && (i - dq.peekFirst()[0] >= delay)) {
                int[] remove = dq.removeFirst();
                count = (count + remove[1]) % MOD;
                temp.offer(remove);
            }
            while (!temp.isEmpty()) {
                dq.addFirst(temp.removeLast());
            }

            if (count > 0) dq.addLast(new int[]{i, count});
        }

        int sum = 0;
        while (!dq.isEmpty()) {
            sum = (sum + dq.removeFirst()[1]) % MOD;
        }
        return sum;
    }

    public static void main(String[] args) {
        var obj = new PeopleAwareOfSecret_2327();
        int ans = obj.peopleAwareOfSecret(6, 2, 4);
        System.out.println("ans = " + ans);
    }
}
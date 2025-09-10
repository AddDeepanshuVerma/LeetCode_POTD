package queue;

import java.util.ArrayDeque;

class PeopleAwareOfSecret_2327_2 {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        final int MOD = (int) (1e9 + 7);
        ArrayDeque<int[]> know = new ArrayDeque<>(); // {# of users, day they knew the secret}
        ArrayDeque<int[]> canShare = new ArrayDeque<>(); // {# of users, day they knew the secret}

        know.addLast(new int[]{1, 1});
        for (int d = 2; d <= n; d++) {
            // first expire all those who were sharing the secret & now they are going to forget
            while (!canShare.isEmpty() && d - canShare.getLast()[1] == forget) {
                canShare.removeLast();
            }

            // now those who know the secret & their delay period is completed can be transferred to canShare deque
            while (!know.isEmpty() && d - know.getLast()[1] >= delay) {
                int[] last = know.removeLast();
                canShare.addFirst(last);
            }

            // now the number of people who can share the secret make n more people to know the secret
            int count = 0;
            for (int[] share : canShare) count = (count + share[0]) % MOD;
            if (count > 0) know.addFirst(new int[]{count, d});
        }

        // now we need to return at the last day how many knew the secret & how many could share it
        int count = 0;
        for (int[] person : know) count = (count + person[0]) % MOD;
        for (int[] person : canShare) count = (count + person[0]) % MOD;

        return count;
    }

    public static void main(String[] args) {
        var obj = new PeopleAwareOfSecret_2327_2();
        int ans = obj.peopleAwareOfSecret(6, 2, 4);
        System.out.println("ans = " + ans);
    }
}
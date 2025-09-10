package queue;

import java.util.ArrayDeque;

class PeopleAwareOfSecret_2327_3 {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        final int MOD = (int) (1e9 + 7);
        ArrayDeque<int[]> know = new ArrayDeque<>();  // {day they knew the secret, # of users}
        ArrayDeque<int[]> share = new ArrayDeque<>(); // {day they knew the secret, # of users}

        know.add(new int[]{1, 1});
        int knowCnt = 1;
        int shareCnt = 0;
        for (int i = 2; i <= n; i++) {
            // known ones are eligible to share to secret, transfer known -> share
            if (!know.isEmpty() && know.peekFirst()[0] == i - delay) {
                int[] first = know.pollFirst();
                knowCnt = (knowCnt - first[1] + MOD) % MOD;
                shareCnt = (shareCnt + first[1]) % MOD;
                share.add(first);
            }
            // ones, that were sharing & now they have forgotten, remover from share
            if (!share.isEmpty() && share.peekFirst()[0] == i - forget) {
                int[] first = share.pollFirst();
                shareCnt = (shareCnt - first[1] + MOD) % MOD;
            }

            // now we are eligible to share on this current day are sharing secret to new people, will be categorized in known ones
            if (!share.isEmpty()) {
                knowCnt = (knowCnt + shareCnt) % MOD;
                know.add(new int[]{i, shareCnt});
            }
        }

        return (knowCnt + shareCnt) % MOD;
    }

    public static void main(String[] args) {
        var obj = new PeopleAwareOfSecret_2327_3();
        int ans = obj.peopleAwareOfSecret(6, 2, 4);
        System.out.println("ans = " + ans);
    }
}
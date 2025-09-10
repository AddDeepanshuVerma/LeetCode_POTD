package array;

import java.util.stream.IntStream;

class MinOperations_3495_3 {
    /*
    * Changes :
        1. Keeping all ranges value, preComputed
        2. Using Stream API to fill power array
    * */
    public long minOperations(int[][] queries) {
        long ans = 0;

        int max = getOperation((long) 1e9) + 1;
        long[] power = new long[max];
        power[0] = 1;
        IntStream.range(1, max)
                .forEach(i -> power[i] = power[i - 1] << 2);

        for (int[] query : queries) {
            long l = query[0], r = query[1];
            long sum = 0;
            for (int i = 1; i < max; i++) {
                long L = power[i - 1];
                long R = power[i] - 1;

                L = Math.max(L, l);
                R = Math.min(R, r);
                if (L <= R) sum += i * (R - L + 1);
            }
            ans += (sum + 1) >> 1;
        }

        return ans;
    }

    private int getOperation(long val) {
        int count = 0;
        while (val > 0) {
            val >>= 2;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        var obj = new MinOperations_3495_3();
        int[][] queries = {{5, 8}};
        long ans = obj.minOperations(queries);
        System.out.println("ans = " + ans);

    }
}
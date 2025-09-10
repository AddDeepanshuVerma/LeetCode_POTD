package array;

class MinOperations_3495_compact {
    // getting rid of streamAPI + getOperation code for faster execution
    public long minOperations(int[][] queries) {
        long ans = 0;
        long[] power = new long[16];
        power[0] = 1;
        for (int i = 1; i < 16; i++) power[i] = power[i - 1] << 2;

        for (int[] query : queries) {
            long l = query[0], r = query[1];
            long sum = 0;
            for (int i = 1; i < 16; i++) {
                long L = Math.max(power[i - 1], l);
                long R = Math.min(power[i] - 1, r);
                if (L <= R) sum += i * (R - L + 1);
            }
            ans += (sum + 1) >> 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        var obj = new MinOperations_3495_compact();
        int[][] queries = {{5, 8}};
        long ans = obj.minOperations(queries);
        System.out.println("ans = " + ans);

    }
}
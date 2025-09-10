package array;

class MinOperations_3495_2 {
    /*
     Optimization changes:
        1. with respect to each [l, r] we are calculating the specific range need to work on
        2. Calculating it ranges L & R point using shift operators
     * */
    public long minOperations(int[][] queries) {
        long ans = 0;
        for (int[] query : queries) {
            long l = query[0], r = query[1];
            long min = getOperation(l);
            long max = getOperation(r);

            long count = 0;
            while (min <= max) {
                long L = 1L << ((min - 1) << 1);
                long R = (1L << (min << 1)) - 1;
                L = Math.max(l, L);
                R = Math.min(R, r);
                if (L <= R) {
                    count = count + (R - L + 1) * min;
                }
                min++;
            }
            ans += (count + 1) >> 1;
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
        var obj = new MinOperations_3495_2();
        int[][] queries = {{5, 8}};
        long ans = obj.minOperations(queries);
        System.out.println("ans = " + ans);

    }
}
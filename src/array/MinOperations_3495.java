package array;

class MinOperations_3495 {
    /*
     * for each query we are calculating, how much of total operations will be needing by each individual number to get to 0
     * To do so: for each query range :
     * 1. we are checking what is the max operation we can go upto first using getMaxOperation(max value);
     * 2. now we start with 1 operation, does the given range fall into this operation
     * 3. if it does then how many number fall, length = R - L + 1
     * 4. sum of those, now we have a count which has total number of operations, now as we are dealing in pair, get ceiling(v+1 >> 1)
     * 5. keep the cumulative count in variable to return
     * */
    public long minOperations(int[][] queries) {
        long ans = 0;
        for (int[] query : queries) {
            long l = query[0], r = query[1];
            int max = getMaxOperation(r);

            long operation = 1, prev = 0, count = 0;
            while (operation <= max) {
                long L = prev + 1;
                long R = (L << 2) - 1;
                prev = R;
                L = Math.max(l, L);
                R = Math.min(R, r);
                if (L <= R) {
                    count = count + (R - L + 1) * operation;
                }
                operation++;
            }
            ans += (count + 1) >> 1;
        }

        return ans;
    }

    private int getMaxOperation(long val) {
        int count = 0;
        while (val > 0) {
            val >>= 2;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        var obj = new MinOperations_3495();
        int[][] queries = {{5, 8}};
        long ans = obj.minOperations(queries);
        System.out.println("ans = " + ans);

    }
}
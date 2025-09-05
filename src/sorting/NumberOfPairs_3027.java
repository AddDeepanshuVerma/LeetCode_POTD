package sorting;

import java.util.Arrays;

class NumberOfPairs_3027 {
    public int numberOfPairs(int[][] points) {
        // we will start processing from minimum x value first
        Arrays.sort(points, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int n = points.length;
        int count = 0;

        for (int i = 0; i < n - 1; i++) {
            int minY = Integer.MIN_VALUE;
            for (int j = i + 1; j < n; j++) {
                // first check is bob should be at lower right side
                if (!lowerRight(i, j, points)) continue;
                // now we need to make sure no other node is inside this rectangle
                int[] B = points[j];
                // bob ka x-axis jo hai vo increasing order me + y-axis bhi decreasing order me ha
                if (B[1] > minY) {
                    count++;
                    minY = B[1];
                }
            }
        }

        return count;
    }

    private boolean lowerRight(int i, int j, int[][] points) {
        int[] A = points[i];
        int[] B = points[j];
        return A[0] <= B[0] && A[1] >= B[1];
    }

    public static void main(String[] args) {
        var obj = new NumberOfPairs_3027();
        int[][] points = {{6,2},{4,4},{2,6}};
        int count = obj.numberOfPairs(points);
        System.out.println("count = " + count);
    }
}
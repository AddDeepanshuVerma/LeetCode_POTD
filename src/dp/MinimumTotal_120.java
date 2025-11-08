package dp;

import java.util.Arrays;
import java.util.List;

class MinimumTotal_120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = 0;
        int col = 0;
        int[][] dp = new int[triangle.size()][triangle.size()];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MIN_VALUE);
        }

        return dfs(row, col, triangle, dp);
    }

    private int dfs(int row, int col, List<List<Integer>> triangle, int[][] dp) {
        if (row >= triangle.size()) return 0;
        if (col >= triangle.get(row).size()) return 0;
        if (dp[row][col] != Integer.MIN_VALUE) return dp[row][col];

        int val = triangle.get(row).get(col);
        int left = dfs(row + 1, col, triangle, dp);
        int right = dfs(row + 1, col + 1, triangle, dp);

        return dp[row][col] = val + Math.min(left, right);
    }
}
package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MinimumTotal_120_bottomUp {
    public int minimumTotal(List<List<Integer>> arr) {
        int n = arr.size();
        int[][] dp = new int[n + 1][n + 1];
        // put preRequisite values in first column
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < arr.get(i).size(); j++) {
                dp[i + 1][j + 1] = arr.get(i).get(j);
            }
        }
        Arrays.stream(dp).forEach(a -> System.out.println(Arrays.toString(a)));

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int top = dp[i - 1][j];
                int topLeft = dp[i - 1][j - 1];
                dp[i][j] += Math.min(top, topLeft);
            }
        }

        System.out.println("==================================");
        Arrays.stream(dp).forEach(a -> System.out.println(Arrays.toString(a)));
        return dp[n][n];

        /*List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) res.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            List<Integer> col = arr.get(i);
            for (int j = 0; j < col.size(); j++) {
                int topLeft = ((i - 1) >= 0 && (j - 1) >= 0) ? arr.get(i - 1).get(j) : 0;
                int top = (i - 1) >= 0 ? arr.get(i - 1).get(j) : 0;
                int min = arr.get(i).get(j) + Math.min(top, topLeft);
                res.get(i).add(min);
            }
        }

        return res.get(n - 1).get(n - 1);*/
    }

    public static void main(String[] args) {
        List<List<Integer>> arr = new ArrayList<>(List.of(List.of(2), List.of(3, 4), List.of(6, 5, 7), List.of(4, 1, 8, 3)));
        var obj = new MinimumTotal_120_bottomUp();
        int ans = obj.minimumTotal(arr);
        System.out.println("ans = " + ans);
    }

}
package dp;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

class MaximumTotalDamage_3186_2 {
    public long maximumTotalDamage(int[] power) {
        TreeMap<Long, Integer> map = new TreeMap<>();
        for (int p : power) map.merge((long)p, 1, Integer::sum);

        List<long[]> list = new ArrayList<>(); // each spell with its totalDamage
        for (var item : map.entrySet()) list.add(new long[]{item.getKey(), item.getValue() * item.getKey()});

        int i = 0, n = list.size();
        long[] dp = new long[n + 1];
        // Arrays.fill(dp, -1);
        return dfs(i, list, dp);
    }

    long dfs(int i, List<long[]> list, long[] dp) {
        if (i >= list.size()) return 0;
        // if (dp[i] != -1) return dp[i];
        if (dp[i] != 0) return dp[i];

        int nextIdx = findNextIndex(list.get(i)[0] + 2, list);
        long take = list.get(i)[1] + dfs(nextIdx, list, dp);
        long skip = dfs(i + 1, list, dp);

        return dp[i] = Math.max(take, skip);
    }

    // this will send us the index of item where power value is > target
    int findNextIndex(long target, List<long[]> list) {
        int start = 0;
        int end = list.size() - 1;
        int ans = list.size();

        while (start <= end) {
            int mid = start + end >>> 1;
            long val = list.get(mid)[0];
            if (val <= target) {
                start = mid + 1;
            } else {
                ans = mid;
                end = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        var obj = new MaximumTotalDamage_3186_2();
        long abs = obj.maximumTotalDamage(new int[]{1, 1, 3, 4});
        System.out.println("abs = " + abs);
    }
}
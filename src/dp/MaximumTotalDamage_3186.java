package dp;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

class MaximumTotalDamage_3186 {
    public long maximumTotalDamage(int[] power) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int p : power) {
            map.merge(p, 1, Integer::sum);
        }

        List<int[]> list = new ArrayList<>(); // each spell with its totalDamage
        for (var item : map.entrySet()) {
            list.add(new int[]{item.getKey(), item.getValue() * item.getKey()});
        }

        int i = 0;
        return dfs(i, list);
    }

    long dfs(int i, List<int[]> list) {
        if (i >= list.size()) return 0;

        int nextIdx = findNextIndex(list.get(i)[0] + 2, list);
        long take = list.get(i)[1] + dfs(nextIdx, list);
        long skip = dfs(i + 1, list);

        return Math.max(take, skip);
    }

    // this will send us the index of item where power value is > target
    int findNextIndex(int target, List<int[]> list) {
        int start = 0;
        int end = list.size() - 1;
        int ans = list.size();

        while (start <= end) {
            int mid = start + end >>> 1;
            int val = list.get(mid)[0];
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
        var obj = new MaximumTotalDamage_3186();
        long abs = obj.maximumTotalDamage(new int[]{1, 1, 3, 4});
        System.out.println("abs = " + abs);
    }
}
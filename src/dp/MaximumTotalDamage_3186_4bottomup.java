package dp;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

// using stream API & bottomUp approach
class MaximumTotalDamage_3186_4bottomup {
    public long maximumTotalDamage(int[] power) {
        List<long[]> list = Arrays.stream(power)
                                        .boxed()
                                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                                        .entrySet()
                                        .stream()
                                        .map(item -> new long[]{item.getKey(), item.getKey() * item.getValue()})
                                        .sorted(Comparator.comparingLong(a -> a[0]))
                                        .toList();
        int n = list.size();
        long ans = 0;
        long[] t = new long[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            long skip = (i + 1 < n) ? t[i + 1] : 0;
            int nextIndex = findNextIndex(list.get(i)[0] + 2, list);
            long take = list.get(i)[1] + (nextIndex < n ? t[nextIndex] : 0);

            t[i] = Math.max(take, skip);
            ans = Math.max(ans, t[i]);
        }

        return ans;
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
        var obj = new MaximumTotalDamage_3186_4bottomup();
        long abs = obj.maximumTotalDamage(new int[]{1, 1, 3, 4});
        System.out.println("abs = " + abs);
    }
}
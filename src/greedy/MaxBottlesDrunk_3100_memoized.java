package greedy;

import java.util.HashMap;
import java.util.Map;

class MaxBottlesDrunk_3100_memoized {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        Map<String, Integer> memo = new HashMap<>();
        return dfs(numBottles, 0, numExchange, memo);
    }

    int dfs(int full, int empty, int exch, Map<String, Integer> memo) {
        String key = full + "," + empty + "," + exch;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        int res = 0;
        
        // Drink all full bottles
        if (full > 0) {
            res = Math.max(res, full + dfs(0, empty + full, exch, memo));
        }
        
        // Exchange empty bottles for one full bottle
        if (empty >= exch) {
            res = Math.max(res, dfs(full + 1, empty - exch, exch + 1, memo));
        }
        
        memo.put(key, res);
        return res;
    }
}
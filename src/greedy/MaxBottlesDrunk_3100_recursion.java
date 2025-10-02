package greedy;

class MaxBottlesDrunk_3100_recursion {

    public static void main(String[] args) {
        var obj = new MaxBottlesDrunk_3100_recursion();
        int ans = obj.maxBottlesDrunk(13, 6);
    }

    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int full = numBottles; // represent full bottles available to drink
        return dfs(full, 0, numExchange);
    }

    int dfs(int full, int empty, int exch) {
        int res = 0;
        if (full > 0) {
            res = Math.max(res, full + dfs(0, empty + full, exch));
        }
        if (empty > 0) {
            res = Math.max(res, dfs(full + 1, empty - 1, exch + 1));
        }

        return res;
    }

}
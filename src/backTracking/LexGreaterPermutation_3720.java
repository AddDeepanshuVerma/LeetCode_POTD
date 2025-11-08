package backTracking;

// TLE : BruteForce, exploring all premutation of s (TC : !n)
class LexGreaterPermutation_3720 {
    String result = "";

    public String lexGreaterPermutation(String s, String target) {
        int[] map = new int[26];
        for (char ch : s.toCharArray()) {
            map[ch - 'a']++;
        }

        String res = "";
        dfs(res, map, target);

        return result;
    }

    private boolean dfs(String res, int[] map, String target) {
        if (res.length() == target.length()) {
            if (res.compareTo(target) > 0) {
                result = res;
                return true;
            }
            return false;
        }

        for (int i = 0; i < 26; i++) {
            if (map[i] > 0) {
                map[i]--;
                if (dfs(res + (char) (i + 'a'), map, target)) {
                    return true;
                }
                map[i]++;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        var obj = new LexGreaterPermutation_3720();
        String ans = obj.lexGreaterPermutation("leet", "code");
        System.out.println("ans = " + ans);
    }

}
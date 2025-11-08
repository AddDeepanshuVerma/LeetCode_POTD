package backTracking;

// pass all test case but not the best TC
// pruning : using StringBuilder instead of String, not exploring if prefix itself is not greater (equal is fine till last).
class LexGreaterPermutation_3720_2 {
    public String lexGreaterPermutation(String s, String target) {
        int[] map = new int[26];
        for (char ch : s.toCharArray()) {
            map[ch - 'a']++;
        }
        StringBuilder res = new StringBuilder();
        dfs(res, map, target);

        return res.toString();
    }

    private boolean dfs(StringBuilder res, int[] map, String target) {
        if (!res.isEmpty() && res.toString()
                                 .compareTo(target.substring(0, res.length())) < 0) {
            return false;
        }
        if (res.length() == target.length()) {
            String temp = res.toString();
            return temp.compareTo(target) > 0;
        }

        for (int i = 0; i < 26; i++) {
            if (map[i] > 0) {
                map[i]--;
                res.append((char) (i + 'a'));
                if (dfs(res, map, target)) {
                    return true;
                }
                res.deleteCharAt(res.length() - 1);
                map[i]++;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        var obj = new LexGreaterPermutation_3720_2();
        String ans = obj.lexGreaterPermutation("abc", "bba");
        System.out.println("ans = " + ans);
    }

}
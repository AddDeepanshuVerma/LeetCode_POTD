package backTracking;

// previous code + custom CompareTo method
class LexGreaterPermutation_3720_3 {
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
        if (!res.isEmpty() && isSmaller(res, target)) {
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

    private static boolean isSmaller(StringBuilder res, String target) {
        for (int i = 0; i < res.length(); i++) {
            if (res.charAt(i) != target.charAt(i)) {
                return res.charAt(i) < target.charAt(i);
            }
        }
        return false;
    }


    public static void main(String[] args) {
        var obj = new LexGreaterPermutation_3720_3();
        String ans = obj.lexGreaterPermutation("abc", "bba");
        System.out.println("ans = " + ans);
    }

}
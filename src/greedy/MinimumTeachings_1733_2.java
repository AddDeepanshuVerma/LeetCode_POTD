package greedy;

import java.util.HashSet;

class MinimumTeachings_1733_2 {

    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int totalUsers = languages.length;
        HashSet<Integer> sadUsers = new HashSet<>();
        for (int[] friend : friendships) {
            int a = friend[0];
            int b = friend[1];
            if (!isCommon(n, languages[a-1], languages[b-1])) {
                sadUsers.add(a);
                sadUsers.add(b);
            }
        }

        int[] arr = new int[n + 1];
        for (Integer sadUser : sadUsers) {
            for (int lang : languages[sadUser - 1]) {
                arr[lang]++;
            }
        }

        int maxCommon = 0;
        for (int i = 0; i < arr.length; i++) {
            maxCommon = Math.max(maxCommon, arr[i]);
        }

        return sadUsers.size() - maxCommon;
    }

    private boolean isCommon(int n, int[] language1, int[] language2) {
        int[] arr = new int[n + 1];
        for (int i : language1) arr[i]++;
        for (int i : language2) if (arr[i] > 0) return true;
        return false;
    }

    public static void main(String[] args) {
        var obj = new MinimumTeachings_1733_2();
        int n = 2;
        int[][] languages = {{1}, {2}, {1, 2}};
        int[][] friendships = {{1, 2}, {1, 3}, {2, 3}};

        int ans = obj.minimumTeachings(n, languages, friendships);
        System.out.println("ans = " + ans);
    }
}
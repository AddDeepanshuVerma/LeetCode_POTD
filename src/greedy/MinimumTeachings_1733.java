package greedy;

import java.util.*;

class MinimumTeachings_1733 {

    /*
    Input: n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
    Output: 2
    Explanation: Teach the third language to users 1 and 3, yielding two users to teach.
    */
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int users = languages.length;
        List<HashSet<Integer>> list = new ArrayList<>();
        list.add(new HashSet<>());
        for (int[] language : languages) {
            HashSet<Integer> set = new HashSet<>();
            for (int lan : language) {
                set.add(lan);
            }
            list.add(set);
        }

        List<int[]> canTalkFriends = new ArrayList<>();
        for (int[] friendship : friendships) {
            int a = friendship[0];
            int b = friendship[1];
            HashSet<Integer> setA = list.get(a);
            HashSet<Integer> setB = list.get(b);
            if (Collections.disjoint(setA, setB)) {
                canTalkFriends.add(new int[]{a, b});
            }
        }
        if (canTalkFriends.isEmpty()) return 0;

        int min = users;
        for (int lang = 1; lang <= n; lang++) {
            int user = needToTeach(lang, list, canTalkFriends);
            System.out.println("lang = " + lang + ", to users = " + user);
            min = Math.min(min, user);
        }

        return min;
    }

    private int needToTeach(int lang, List<HashSet<Integer>> list, List<int[]> canTalkFriends) {
        int count = 0;
        HashSet<Integer> know = new HashSet<>();
        for (int[] canTalkFriend : canTalkFriends) {
            int a = canTalkFriend[0];
            int b = canTalkFriend[1];
            if (!know.contains(a) && !list.get(a).contains(lang)) {
                count++;
                know.add(a);
            }
            if (!know.contains(b) && !list.get(b).contains(lang)) {
                count++;
                know.add(b);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        var obj = new MinimumTeachings_1733_2();
        int n = 2;
        int[][] languages = {{2}, {1}, {2, 1}, {1}, {1, 2}, {1}, {2}, {1}, {1}, {2}, {1, 2}, {1, 2}, {1, 2}, {2, 1}, {1}, {2}, {1, 2}};
        int[][] friendships = {{15, 16}, {4, 13}, {3, 16}, {5, 14}, {1, 7}, {2, 11}, {3, 15}, {4, 16}, {7, 9}, {6, 13}, {6, 16}, {4, 10}, {6, 9}, {5, 6},
                {7, 12}, {6, 12}, {3, 7}, {4, 7}, {8, 10}};

        int ans = obj.minimumTeachings(n, languages, friendships);
        System.out.println("ans = " + ans);
    }
}
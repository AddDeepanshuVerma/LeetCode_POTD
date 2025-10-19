package bfs;

import java.util.*;

// read problem incorrectly, solved completely different imaginable problem
class FindLexSmallestString_1625 {

    private static final int[][] dp;

    static {
        dp = new int[10][10];
        Arrays.stream(dp)
              .forEach(arr -> Arrays.fill(arr, Integer.MAX_VALUE));
        Arrays.fill(dp[0], 0); // if value is already zero we can not minimize it more
        calculateTheMinimumValue(9, 7, dp);

        for (int val = 1; val <= 9; val++) {
            for (int add = 1; add <= 9; add++) {
                calculateTheMinimumValue(val, add, dp);
            }
        }
    }

    private static void calculateTheMinimumValue(int val, int add, int[][] dp) {
        if (dp[val][add] != Integer.MAX_VALUE) return;
        Set<Integer> set = new HashSet<>();
        int min = Integer.MAX_VALUE;
        int temp = val;

        while (!set.contains(temp)) {
            set.add(temp);
            min = Math.min(min, temp);
            temp = (temp + add) % 10;
        }

        dp[val][add] = min;
    }

    public String findLexSmallestString(String s, int a, int b) {
        for (int[] ints : dp) System.out.println(Arrays.toString(ints));

        StringBuilder sb = new StringBuilder();
        int n = s.length();
        if ((b & 1) == 1) { // b is odd, we will minimize all indices
            for (int i = 0; i < n; i++) {
                sb.append(dp[s.charAt(i) - '0'][a]);
            }
        } else { // b is even hence we cna only change idd indices
            for (int i = 0; i < n; i++) {
                int ch = s.charAt(i) - '0';
                int min = dp[s.charAt(i) - '0'][a];
                sb.append((i & 1) == 1 ? min : ch);
            }
        }

        s = sb.toString();
        String lexSmallestString = s;
        for (int start = b; start < n; start += b) {
            int idx = n - start;
            String temp = s.substring(idx) + s.substring(0, idx);
            System.out.println("temp = " + temp);
            if (temp.compareTo(lexSmallestString) < 0) {
                lexSmallestString = temp;
            }
        }


        return lexSmallestString;
    }

    public static void main(String[] args) {
        var obj = new FindLexSmallestString_1625();
        String min = obj.findLexSmallestString("43987654", 7, 3);
        System.out.println("min = " + min);
    }
}
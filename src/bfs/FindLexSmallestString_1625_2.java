package bfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

// BFS approach, traversed all possible String as there can be max 10 by adding in odd * n by rotating
// hence total possible nonRepeating would be = 10 * n *(subString at each state hence n)
// while if we assume it a tree where we are considering all repeating state as well, n * (2 ^ (10 * n)) which would be wrong
class FindLexSmallestString_1625_2 {
    public String findLexSmallestString(String s, int a, int b) {
        int n = s.length();
        String smallest = s;
        Deque<String> q = new ArrayDeque<>();
        Set<String> seen = new HashSet<>();

        q.addFirst(s);
        seen.add(s);

        while (!q.isEmpty()) {
            String curr = q.removeFirst();
            if (curr.compareTo(smallest) < 0) {
                smallest = curr;
            }

            StringBuilder odd = new StringBuilder();
            for (int i = 0; i < n; i++) {
                int c = curr.charAt(i) - '0';
                if ((i & 1) == 1) odd.append(((c + a) % 10));
                else odd.append(c);
            }
            if (seen.add(odd.toString())) q.addFirst(odd.toString());

            String rotated = curr.substring(n - b) + curr.substring(0, n - b);
            if (seen.add(rotated)) q.addFirst(rotated);
        }

        return smallest;
    }

    public static void main(String[] args) {
        var obj = new FindLexSmallestString_1625_2();
        String min = obj.findLexSmallestString("43987654", 7, 3);
        System.out.println("min = " + min);
    }
}
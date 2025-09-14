package hashTable;

import java.util.HashMap;
import java.util.HashSet;

class Spellchecker_966_cmpt {
    final int mask = (1 << (0)) | (1 << ('e' - 'a')) | (1 << ('i' - 'a')) | (1 << ('o' - 'a')) | (1 << ('u' - 'a'));

    public String[] spellchecker(String[] wordlist, String[] queries) {
        int n = queries.length;
        String[] res = new String[n];

        HashSet<String> set = new HashSet<>();
        HashMap<String, Integer> mapLower = new HashMap<>(), mapVowel = new HashMap<>();

        for (int i = 0; i < wordlist.length; i++) {
            String s = wordlist[i];
            set.add(s);
            mapLower.putIfAbsent(s.toLowerCase(), i);               // we keep the first occurance index of this String
            mapVowel.putIfAbsent(getVowelProne(s), i);// we keep the first occurance index of this String
        }

        for (int i = 0; i < n; i++) {
            String query = queries[i];
            String queryLower = query.toLowerCase();
            String queryVowel = getVowelProne(query);

            if (set.contains(query)) res[i] = query;
            else if (mapLower.containsKey(queryLower)) res[i] = wordlist[mapLower.get(queryLower)];
            else if (mapVowel.containsKey(queryVowel)) res[i] = wordlist[mapVowel.get(queryVowel)];
            else res[i] = "";
        }

        return res;
    }

    private String getVowelProne(String s) {
        s = s.toLowerCase();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (isVowel(arr[i])) {
                arr[i] = '.';
            }
        }
        return String.valueOf(arr);
    }

    boolean isVowel(char ch) {
        return (mask & (1 << (ch - 'a'))) > 0;
    }

    public static void main(String[] args) {
        var obj = new Spellchecker_966_cmpt();
        boolean ans = obj.isVowel('h');
        System.out.println("ans = " + ans);
    }
}
package hashTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class Spellchecker_966 {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        int n = queries.length;
        String[] res = new String[n];

        HashSet<String> set = new HashSet<>();
        HashMap<String, Integer> mapLower = new HashMap<>();
        HashMap<String, Integer> mapVowel = new HashMap<>();

        for (int i = 0; i < wordlist.length; i++) {
            String s = wordlist[i];
            set.add(s);
            mapLower.putIfAbsent(s.toLowerCase(), i); // we keep the first occurance index of this String
            mapVowel.putIfAbsent(getVowelProne(s.toLowerCase()), i);// we keep the first occurance index of this String
        }

        Arrays.fill(res, ""); // for default values
        for (int i = 0; i < n; i++) {
            String query = queries[i];
            String lowerCase = query.toLowerCase();

            if (set.contains(query)) {
                // first search if exactly same is there
                res[i] = query;
            } else {
                if (mapLower.containsKey(lowerCase)) {
                    // then search if case-sensitive it there
                    int idx = mapLower.get(lowerCase);
                    res[i] = wordlist[idx];
                } else if (mapVowel.containsKey(getVowelProne(lowerCase))) {
                    // now search with vowel alteration
                    int idx = mapVowel.get(getVowelProne(lowerCase));
                    res[i] = wordlist[idx];
                }
            }
        }

        return res;
    }

    private String getVowelProne(String s) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
                arr[i] = '.';
            }
        }
        return String.valueOf(arr);
    }
}
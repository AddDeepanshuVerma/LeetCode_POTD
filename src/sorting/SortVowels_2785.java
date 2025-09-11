package sorting;

import java.util.ArrayList;
import java.util.List;

class SortVowels_2785 {
    public String sortVowels(String s) {
        String vowel = "aeiouAEIOU";
        List<Character> list = new ArrayList<Character>();

        for (char ch : s.toCharArray()) {
            if (vowel.contains(String.valueOf(ch))) {
                list.add(ch);
            }
        }

        list.sort((a, b) -> a - b);

        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (char ch : s.toCharArray()) {
            if (vowel.contains(String.valueOf(ch))) {
                sb.append(list.get(i));
                i++;
            } else sb.append(ch);
        }

        return sb.toString();
    }


    // ============================ using switch to find vowel =========================
    public String sortVowels2(String s) {
        List<Character> list = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (isVowel(c)) list.add(c);
        }

        list.sort(Character::compareTo);
        int i = 0;

        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (isVowel(c)) sb.append(list.get(i++));
            else sb.append(c);
        }

        return sb.toString();
    }

    private boolean isVowel(char c) {
        return switch (Character.toLowerCase(c)) {
            case 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' -> true;
            default -> false;
        };
    }

}
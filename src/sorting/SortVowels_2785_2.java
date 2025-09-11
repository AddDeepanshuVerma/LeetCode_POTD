package sorting;

import java.util.ArrayList;
import java.util.List;

class SortVowels_2785_2 {
    public String sortVowels2(String s) {
        char[] arr = s.toCharArray();
        List<Character> list = new ArrayList<>();
        for (char c : arr) {
            if (isVowel(c)) list.add(c);
        }

        list.sort(Character::compareTo);

        for (int j = 0, i = 0; j < arr.length; j++) {
            if (isVowel(arr[j])) arr[j] = list.get(i++);
        }

        return String.valueOf(arr);
    }

    private boolean isVowel(char c) {
        return switch (Character.toLowerCase(c)) {
            case 'a', 'e', 'i', 'o', 'u' -> true;
            default -> false;
        };
    }

}
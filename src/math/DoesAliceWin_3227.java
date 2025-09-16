package math;

class DoesAliceWin_3227 {
    public boolean doesAliceWin(String s) {
        int count = 0;
        for (char ch : s.toCharArray()) {
            if (isVowel(ch)) {
                count++;
            }
        }
        // if count is odd then simply Alice choose picks complete String
        if ((count & 1) == 1) return true;
        // if count is zero, Bob wins
        // but if count > 0 & even ( 2, 4, 6...) then Alice pick even, bob pick odd; even remains hence Alice wins
        return count > 0 && count <= s.length();
    }

    // hence if even a single vowel present in String, Alice wins
    public boolean doesAliceWin2(String s) {
        return s.chars().anyMatch(ch -> isVowel((char) ch));
    }

    static boolean isVowel(char ch) {
        return switch (ch) {
            case 'a', 'e', 'i', 'o', 'u' -> true;
            default -> false;
        };
    }

    public static void main(String[] args) {
        var obj = new DoesAliceWin_3227();
        boolean ans = obj.doesAliceWin("ao");
        System.out.println("ans = " + ans);
    }
}
package math;

import java.util.HashMap;

class FractionToDecimal_166 {
    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuilder sb = new StringBuilder();
        // handle sign
        if (numerator < 0 ^ denominator < 0) {
            sb.append("-");
        }
        // handle int overFlow
        long num = Math.abs((long) numerator);
        long deno = Math.abs((long) denominator);

        long val = num / deno;
        long rem = num % deno;

        sb.append(val);
        if (rem == 0) return sb.toString();
        sb.append(".");

        HashMap<Long, Integer> map = new HashMap<>(); // stores {reminder, index}
        while (rem != 0) {
            if (map.containsKey(rem)) {
                int idx = map.get(rem);
                sb.insert(idx, "(");
                sb.append(")");
                break;
            }
            map.put(rem, sb.length());
            rem = rem * 10;
            sb.append(rem / deno);
            rem = rem % deno;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        // System.out.println(fractionToDecimal(4, 333));
        System.out.println(fractionToDecimal(1, 2));
    }
}
package designing;

import java.util.Arrays;

class Spreadsheet {

    private final int[][] arr;

    public Spreadsheet(int rows) {
        arr = new int[rows + 1][26];
    }

    public void setCell(String cell, int value) {
        int col = cell.charAt(0) - 'A';
        int row = Integer.parseInt(cell.substring(1));
        arr[row][col] = value;
    }

    public void resetCell(String cell) {
        int col = cell.charAt(0) - 'A';
        int row = Integer.parseInt(cell.substring(1));
        arr[row][col] = 0;
    }

    public int getValue(String formula) {
        formula = formula.substring(1);
        String[] split = formula.split("[+]");
        int xValue = getCorrectValue(split[0]);
        int yValue = getCorrectValue(split[1]);

        return xValue + yValue;
    }

    private int getCorrectValue(String s) {
        if (Character.isAlphabetic(s.charAt(0))) {
            int col = s.charAt(0) - 'A';
            int row = Integer.parseInt(s.substring(1));
            return arr[row][col];
        } else {
            return Integer.parseInt(s);
        }
    }

    public static void main(String[] args) {
        String[] split = "X+Y".split("[+]");
        System.out.println(Arrays.toString(split));
    }
}
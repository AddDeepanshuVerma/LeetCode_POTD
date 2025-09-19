package designing;

class Spreadsheet_1 {

    private final int[][] arr;

    public Spreadsheet_1(int rows) {
        arr = new int[rows + 1][26];
    }

    public void setCell(String cell, int value) {
        int col = getCol(cell);
        int row = getRow(cell);
        arr[row][col] = value;
    }

    public void resetCell(String cell) {
        int col = getCol(cell);
        int row = getRow(cell);
        arr[row][col] = 0;
    }

    public int getValue(String formula) {
        formula = formula.substring(1);
        String[] split = formula.split("[+]");
        int xValue = getCorrectValue(split[0]);
        int yValue = getCorrectValue(split[1]);

        return xValue + yValue;
    }

    private int getCorrectValue(String cell) {
        if (Character.isAlphabetic(cell.charAt(0))) {
            int col = getCol(cell);
            int row = getRow(cell);
            return arr[row][col];
        } else return getRow("A" + cell);
    }

    private static int getCol(String cell) {
        return cell.charAt(0) - 'A';
    }

    private int getRow(String cell) {
        int row = 0;
        char[] arr = cell.toCharArray();
        for (int i = 1; i < arr.length; i++) {
            row = (row * 10) + (arr[i] - '0');
        }
        return row;
    }

}
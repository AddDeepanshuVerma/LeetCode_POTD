package easy;

class SumZero_1304 {

    public int[] sumZero(int n) {
        // if n is ODD we have to maintain a 0 in the array
        int[] res = new int[n];
        for (int i = 0; i < n - 1; i += 2) {
            res[i] = i + 1;
            res[i + 1] = -res[i];
        }
        return res;
    }

    public int[] sumZero2(int n) {
        int[] res = new int[n];
        int num = 1;
        for (int i = 0; i < n - 1; i += 2, num++) {
            res[i] = num;
            res[i + 1] = -num;
        }

        return res;
    }
}
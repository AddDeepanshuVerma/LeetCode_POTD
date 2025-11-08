package TBD;

class MinTime_3494_2 {
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length, m = mana.length;
        long minTime = 0;
        long[] availableAt = new long[n];
        long diff = Long.MAX_VALUE;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (diff != Long.MAX_VALUE) {
                    availableAt[j] = availableAt[j] - diff;
                }

                long timeTaken = minTime + ((long) mana[i] * skill[j]);
                if (availableAt[j] != 0) {
                    diff = Math.min(diff, timeTaken - availableAt[j]);
                }
                availableAt[j] = minTime = timeTaken;
            }
            if (diff != Long.MAX_VALUE) {
                diff = diff - n;
                minTime = minTime - diff;
            }
        }

        return minTime;
    }

    public static void main(String[] args) {
        var obj = new MinTime_3494_2();
        int[] skill = {1, 5, 2, 4}, mana = {5, 1, 4, 2};
        long time = obj.minTime(skill, mana);
        System.out.println("time = " + time);
    }
}
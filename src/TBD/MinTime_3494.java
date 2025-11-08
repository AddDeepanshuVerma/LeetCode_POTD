package TBD;

class MinTime_3494 {
    public long minTime(int[] skill, int[] mana) {
        long minTime = 0;
        long[] availableAt = new long[skill.length];

        for (int i = 0; i < mana.length; i++) {
            long currMana = mana[i];
            // now we know the maxTime we can start is minTime value
            // but, we need to find out the minimum value we can go
            minTime = findMinimumTimeToStartWith(minTime, mana, skill, availableAt);
            for (int j = 0; j < skill.length; j++) {
                long currWizardSkill = skill[j];
                minTime = minTime + currMana * currWizardSkill;
            }
        }

        return minTime;
    }

    private long findMinimumTimeToStartWith(long end, int[] mana, int[] skill, long[] availableAt) {
        long start = 0;
        long minTime = -1;
        while (start <= end) {
            long mid = (start + end) >> 1;
            if (isPossibleToBrewSpell(mid, mana, skill, availableAt)) {
                minTime = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return minTime;
    }

    private boolean isPossibleToBrewSpell(long mid, int[] mana, int[] skill, long[] availableAt) {
        return true;
    }

    public static void main(String[] args) {
        var obj = new MinTime_3494();
        int[] skill = {1, 5, 2, 4}, mana = {5, 1, 4, 2};
        long time = obj.minTime(skill, mana);
        System.out.println("time = " + time);
    }
}
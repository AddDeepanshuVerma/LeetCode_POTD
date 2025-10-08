package BinarySearch;

import java.util.Arrays;

class SuccessfulPairs_2300 {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
    	int n = spells.length;
    	int m = potions.length;
    	
    	int[] ans = new int[n];
    	Arrays.sort(potions);
    	
    	for(int i = 0; i < n; i++) {
    		int lower = getLowerBound(spells[i], potions, success, m);
    		if(lower == -1) continue;
    		ans[i] = m - lower;
    	}
    	
    	return ans;
    }
    
    private int getLowerBound(int a, int[] arr, long target, int m){
    	int start = 0;
    	int end   = m - 1;
    	int ans   = -1;
    	
    	while(start <= end) {
    		int mid = (start + end) >>> 1;
    		long mul = (long)a * arr[mid];
    		if(mul >= target) {
    			ans = mid;
    			end = mid - 1;
    		}else start = mid + 1;
    	}
    	
    	return ans;
    }
}
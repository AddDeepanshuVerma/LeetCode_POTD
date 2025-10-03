package bfs;

import java.util.PriorityQueue;
import java.util.Queue;

class TrapRainWaterZ_407 {
    public int trapRainWater(int[][] arr) {
    	int water = 0;
    	int m = arr.length;
    	int n = arr[0].length;
    	int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    	
    	boolean[][] visited 	= new boolean[m][n];
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)-> a[0] - b[0]); // contains : {current index right, row, col}

		// m * n
    	for(int i = 0; i < m; i++) {
    		for(int j = 0; j < n; j++) {
    			if(i == 0 || i == m - 1) {
    				pq.offer(new int[]{arr[i][j], i, j});
    				visited[i][j] = true;
    			} else if (j == 0 || j == n - 1) {
    				pq.offer(new int[]{arr[i][j], i, j});
    				visited[i][j] = true;
    			}
    		}
    	}

		// m * n * log(m * n), visiting each element single time
    	while(!pq.isEmpty()){
    		int[] poll = pq.poll();
    		int h = poll[0];
    		int r = poll[1];
    		int c = poll[2];
    		
    		for(int[] dir : dirs){
    			int r_ = r + dir[0];
    			int c_ = c + dir[1];
    			if(r_ < 0 || r_ >= m || c_ < 0 || c_ >= n || visited[r_][c_]) {
    				continue;
    			}
    			visited[r_][c_] = true;
    			water += Math.max(0, h - arr[r_][c_]);
    			int max = Math.max(h, arr[r_][c_]);
    			pq.offer(new int[]{max, r_, c_});
    		}
    		
    	}
    	
    	return water;
    }
}
package bfs;

import java.util.Arrays;
import java.util.PriorityQueue;

class SwimInWater_778 {
    public int swimInWater(int[][] grid) {
		int n = grid.length;
		int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

		int[][] time = new int[n][n];
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // {current time, row, col}
		Arrays.stream(time).forEach(arr -> Arrays.fill(arr, Integer.MAX_VALUE));

		time[0][0] = grid[0][0];
		pq.offer(new int[]{grid[0][0], 0, 0});

        while (!pq.isEmpty()) {
            int[] cell = pq.poll();
            int t = cell[0], r = cell[1], c = cell[2];
            if (r == n - 1 && c == n - 1) return t; // reached destination

            for (int[] dir : dirs) {
                int r_ = dir[0] + r;
                int c_ = dir[1] + c;
                if (r_ < 0 || r_ >= n || c_ < 0 || c_ >= n) continue;

                int reachTime = Math.max(t, grid[r_][c_]);
                if (reachTime < time[r_][c_]) {
                    time[r_][c_] = reachTime;
                    pq.offer(new int[]{reachTime, r_, c_});
                }
            }
        }

        return -1;
    }

	public static void main(String[] args) {
		var obj = new SwimInWater_778();
		int ans = obj.swimInWater(new int[][]{{3, 2}, {0, 1}});
		System.out.println("ans = " + ans);
	}
}
package bfs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

class SwimInWater_778_2 {
    public int swimInWater(int[][] grid) {
		int n = grid.length;
		int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

		int[][] time = new int[n][n];
		PriorityQueue<Cell> pq = new PriorityQueue<>(); // {current time, row, col}
		// Arrays.stream(time).forEach(arr -> Arrays.fill(arr, Integer.MAX_VALUE));
        Stream.of(time).forEach(arr -> Arrays.fill(arr, Integer.MAX_VALUE));

		time[0][0] = grid[0][0];
		pq.offer(new Cell(grid[0][0], 0, 0));

        while (!pq.isEmpty()) {
            Cell cell = pq.poll();
            int t = cell.time, r = cell.row, c = cell.col;
            if (r == n - 1 && c == n - 1) return t; // reached destination

            for (int[] dir : dirs) {
                int r_ = dir[0] + r;
                int c_ = dir[1] + c;
                if (r_ < 0 || r_ >= n || c_ < 0 || c_ >= n) continue;

                int reachTime = Math.max(t, grid[r_][c_]);
                if (reachTime < time[r_][c_]) {
                    time[r_][c_] = reachTime;
                    pq.offer(new Cell(reachTime, r_, c_));
                }
            }
        }

        return -1;
    }

    record Cell(Integer time, Integer row, Integer col) implements Comparable<Cell> {

        @Override
        public int compareTo(Cell other) {
            return this.time - other.time;
        }

    }

	public static void main(String[] args) {
		var obj = new SwimInWater_778_2();
		int ans = obj.swimInWater(new int[][]{{3, 2}, {0, 1}});
		System.out.println("ans = " + ans);
	}
}
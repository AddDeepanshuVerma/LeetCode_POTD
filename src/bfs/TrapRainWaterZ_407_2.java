package bfs;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class TrapRainWaterZ_407_2 {
    public int trapRainWater(int[][] arr) {
        int water = 0;
        int m = arr.length, n = arr[0].length;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)-> a[0] - b[0]); // contains : {current index right, row, col}

        // both first & last column being added
        for (int j = 0; j < n; j++) {
            pq.offer(new int[]{arr[0][j], 0, j});
            pq.offer(new int[]{arr[m - 1][j], m - 1, j});
            visited[0][j] = true;
            visited[m - 1][j] = true;
        }
        // both first & last row being added without corner repetition
        for (int i = 1; i < m - 1; i++) {
            pq.offer(new int[]{arr[i][0], i, 0});
            pq.offer(new int[]{arr[i][n - 1], i, n - 1});
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int h = poll[0], r = poll[1], c = poll[2];
            for (int[] dir : dirs) {
                int r_ = r + dir[0], c_ = c + dir[1];
                if (r_ < 0 || r_ >= m || c_ < 0 || c_ >= n || visited[r_][c_]) continue;

                visited[r_][c_] = true;
                water += Math.max(0, h - arr[r_][c_]);
                pq.offer(new int[]{Math.max(h, arr[r_][c_]), r_, c_});
            }
        }

        return water;
    }
}
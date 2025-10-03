package bfs;

import java.util.PriorityQueue;

class TrapRainWaterZ_407_3Interview {
    // Encapsulate direction logic using a constant
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    record Cell(Integer height, Integer row, Integer col) implements Comparable<Cell> {
        @Override
        public int compareTo(Cell other) {
            return Integer.compare(this.height, other.height); // or this.height - other.height;
        }
    }

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length <= 2 || heightMap[0].length <= 2) return 0; // edge case
        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        /*"In general, I prefer coding to the Queue interface for flexibility, but since here we specifically require a min-heap, and won't ever change
        this to another queue type, I'm okay with using PriorityQueue directly. It also gives access to class-specific methods if needed."*/
        // Queue<Cell> pq = new PriorityQueue<>();
        PriorityQueue<Cell> pq = new PriorityQueue<>();

        // Add boundary cells
        for (int j = 0; j < n; j++) {
            pq.offer(new Cell(heightMap[0][j], 0, j));
            pq.offer(new Cell(heightMap[m - 1][j], m - 1, j));
            visited[0][j] = visited[m - 1][j] = true;
        }
        for (int i = 1; i < m - 1; i++) {
            pq.offer(new Cell(heightMap[i][0], i, 0));
            pq.offer(new Cell(heightMap[i][n - 1], i, n - 1));
            visited[i][0] = visited[i][n - 1] = true;
        }

        int water = 0;

        while (!pq.isEmpty()) {
            Cell cell = pq.poll();
            for (int[] dir : DIRS) {
                int r_ = cell.row + dir[0], c_ = cell.col + dir[1];
                if (r_ < 0 || r_ >= m || c_ < 0 || c_ >= n || visited[r_][c_]) continue;

                visited[r_][c_] = true;
                int neighbourHeight = heightMap[r_][c_];
                water += Math.max(0, cell.height - neighbourHeight);
                pq.offer(new Cell(Math.max(cell.height, neighbourHeight), r_, c_));
            }
        }

        return water;
    }
}
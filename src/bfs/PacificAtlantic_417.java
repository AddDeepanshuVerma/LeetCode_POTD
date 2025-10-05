package bfs;

import java.util.*;

class PacificAtlantic_417 {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // a method will return a 2d boolean array of reachable indexes
        // to this method we will send a queue of one of the oceans
        int m = heights.length;
        int n = heights[0].length;

        int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        boolean[][] pacific = getReachableNodes(0, 0, heights, dirs, m, n);
        boolean[][] atlantic = getReachableNodes(m - 1, n - 1, heights, dirs, m, n);

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] & atlantic[i][j]) {
                    res.add(List.of(i, j));
                }
            }
        }

        return res;
    }

    private boolean[][] getReachableNodes(int row, int col, int[][] heights, int[][] dirs, int m, int n) {
        Deque<int[]> q = new ArrayDeque<int[]>(); // {height, row, col}
        boolean[][] visited = new boolean[m][n];
        for (int j = 0; j < n; j++) {
            int h = heights[row][j];
            q.offer(new int[]{h, row, j});
            visited[row][j] = true;
        }
        for (int i = 0; i < m; i++) {
            int h = heights[i][col];
            q.offer(new int[]{h, i, col});
            visited[i][col] = true;
        }

        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int h = cell[0], r = cell[1], c = cell[2];

            for (int[] dir : dirs) {
                int r_ = r + dir[0];
                int c_ = c + dir[1];
                if (r_ < 0 || r_ >= m || c_ < 0 || c_ >= n || visited[r_][c_]) continue;
                if (heights[r_][c_] < h) continue; // water can not flow towards current h
                visited[r_][c_] = true;
                q.offer(new int[]{heights[r_][c_], r_, c_});
            }
        }

        return visited;
    }

    public static void main(String[] args) {
        var obj = new PacificAtlantic_417();
        List<List<Integer>> lists = obj.pacificAtlantic(new int[][]{{2, 1}, {1, 2}});
        System.out.println("lists = " + lists);
    }
}
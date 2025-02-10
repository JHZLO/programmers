import java.util.*;

class Solution {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        que.offer(new int[] {0, 0});
        visited[0][0] = true;
        
        int dis = 1;
        
        while (!que.isEmpty()) {
            int size = que.size();
            for (int s = 0; s < size; s++) {
                int[] pos = que.poll();
                int x = pos[0];
                int y = pos[1];
                
                if (x == n - 1 && y == m - 1) {
                    return dis;
                }
                
                for (int i = 0; i < 4; i++) {
                    int newX = x + dx[i];
                    int newY = y + dy[i];
                    
                    if (newX >= 0 && newY >= 0 && newX < n && newY < m) {
                        if (!visited[newX][newY] && maps[newX][newY] == 1) {
                            que.offer(new int[] {newX, newY});
                            visited[newX][newY] = true;
                        }
                    }
                }
            }
            dis++;
        }
        
        return -1;
    }
}
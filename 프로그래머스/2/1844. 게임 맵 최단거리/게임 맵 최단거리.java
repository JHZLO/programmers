import java.util.*;

class Solution {
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    
    public int solution(int[][] maps) {
        int N, M;
        int answer = 1;
        N = maps.length;
        M = maps[0].length;
        
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        
        que.offer(new int[] {0,0});
        visited[0][0] = true;
        
        while(!que.isEmpty()){
            int size = que.size();
            for (int i = 0; i < size; i++){
                int[] pos = que.poll();
                int x = pos[0];
                int y = pos[1];
                
                if (x == N - 1 && y == M - 1) {
                    return answer;
                }
                
                for (int j = 0; j < 4; j++){
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    
                    if (nx >= 0 && ny >= 0 && nx < N && ny < M){
                        if (!visited[nx][ny] && maps[nx][ny] == 1){
                            que.offer(new int[] {nx, ny});
                            visited[nx][ny] = true;
                        }
                    }
                }
            }
            answer += 1;
        }
        
        return -1;
    }
}
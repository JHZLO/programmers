// /*
// 시추관을 index 1~8까지 차례로 이동

// 1이 나왔을 때 인접한 곳들 탐색 ~> BFS?
// 가장 큰 y값 저장
// -> 그 다음부터 또 탐색
// */
import java.util.*;

// class Solution {
//     private static int max_y;
//     private static int max_x;
    
//     private static int[] dx = {1, -1, 0, 0};
//     private static int[] dy = {0, 0, 1, -1};
    
//     private static int cnt;
    
//     public int solution(int[][] land) {
//         max_x = land[0].length;
//         max_y = land.length;
        
//         int max_cnt = 0;
        
//         for (int i = 0; i < max_x; i++){ // 시추관을 이동
//             cnt = bfs(land, i);
//             max_cnt = Math.max(max_cnt, cnt);
//         }
        
//         return max_cnt;
//     }
    
//     private int bfs(int[][] land, int index){
//         cnt = 0;
//         int max_depth = 0;
//         Queue<int[]> que = new LinkedList<>();
//         boolean[][] visited = new boolean[max_y][max_x];
        
//         for (int i = 0; i < max_y; i++){
//             if (!visited[i][index] && land[i][index] == 1){
//                 que.offer(new int[] {i, index});
//                 visited[i][index] = true;
//                 cnt ++;
//             }else{
//                 continue;
//             }
            
//             while (!que.isEmpty()){
//                 int size = que.size();
                
//                 for (int k = 0; k < size; k ++){
//                     int[] pos = que.poll();
//                     int x = pos[1];
//                     int y = pos[0];

//                     for (int j = 0; j < 4; j++){
//                         int nx = x + dx[j];
//                         int ny = y + dy[j];
                        
//                         if (nx >= 0 && ny >= 0 && nx < max_x && ny < max_y){
//                             if (!visited[ny][nx] && land[ny][nx] == 1){
//                                 que.offer(new int[] {ny, nx});
//                                 visited[ny][nx] = true;
//                                 cnt ++;
//                                 max_depth = Math.max(max_depth, ny);
//                             }
//                         }
//                     }
//                 }
//             }    
//             i = max_depth;
//         }
        
//         return cnt;
//     }
// }
class Solution {
    static int[][] map;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static Set<Integer> useIndex = new HashSet<>();
    static Map<Integer, Integer> store = new HashMap<>();
    
    public int solution(int[][] land){
        int answer = 0;

        map = new int[land.length][land[0].length];
        for(int k = 0; k < land.length; k++){
            map[k] = land[k].clone();
        }
        int oilIndex = -1;
        for(int i = 0; i < land[0].length; i++){
            for(int j = 0; j < land.length; j++){
                if(map[j][i] == 1){
                    int max = bfs(land, j, i, 0, oilIndex--);
                    store.put(oilIndex+1, max);
                }
            }
        }

        for(int i = 0; i < map[0].length; i++){
            int localSum = 0;
            for(int j = 0; j < map.length; j++){
                if(map[j][i] != 0){
                    int oi = map[j][i];
                    if(store.containsKey(oi) && !useIndex.contains(oi)){
                        localSum += store.get(oi);
                        useIndex.add(oi);
                    }
                }
            }
            answer = Math.max(answer, localSum);
            useIndex.clear();
        }

        return answer;
    }

    private static int bfs(int land[][], int x, int y, int count, int oilIndex){
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{x, y});
        map[x][y] = oilIndex;
        land[x][y] = --count;
        int max = 0;
        while(!queue.isEmpty()){
            int[] pos = queue.poll();
            for(int i = 0; i < dx.length; i++){
                int nextX = pos[0] + dx[i];
                int nextY = pos[1] + dy[i];

                if(nextX >= land.length || nextX < 0 || nextY >= land[0].length || nextY < 0) continue;
                if(land[nextX][nextY] != 1) continue;

                map[nextX][nextY] = oilIndex;
                land[nextX][nextY] = --count;
                queue.add(new int[]{nextX, nextY});
                max = Math.max(max, (count*-1));
            }
        }
        if(max == 0) max = 1;
        return max;
    }
}
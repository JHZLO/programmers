/*
<입력>
rectangle : [좌측 하단 x, 좌측 하단 y, 우측 상단 x, 우측 상단 y]
characterX : 시작 지점 x 좌표 (1~50 N)
characterY : 시작 지점 y 좌표 (1~50 N)
itemX : 목표 위치 x 좌표 (1~50 N)
itemY : 목표 위치 y 좌표 (1~50 N)

<출력>
아이템을 줍기 위해 이동해야 하는 가장 짧은 거리 return

<문제풀이>
우선 직사각형이 겹치는 부분을 잘 표현해야함
- 겹쳐진 부분으로는 지나갈 수 없음 -> 직사각형의 가장 바깥쪽 테두리로만 움직일 수 있음
- 내부 면적만 다 0으로 채우면 될 것 같음
- 좌표에 2배를 해줘서 겹치는 부분이 들어가는 걸 방지해야함

*/
import java.util.*;

class Solution {
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {       
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;
        
        int[][] navigation = new int[102][102];
        boolean[][] visited = new boolean[102][102];
        Queue<int[]> que = new LinkedList<>();
        
        for (int i = 0; i < rectangle.length; i++){
            for (int j = 0; j < 4; j++){
                fillOutline(i, j, rectangle, navigation); // 가장 바깥쪽 테두리만 칠함
            }
        }
        
        que.offer(new int[] {characterX, characterY});
        visited[characterY][characterX] = true;
      
        int dis = 0;
        while (!que.isEmpty()){
            int size = que.size();
            for (int k = 0; k < size; k++){
                int[] pos = que.poll();
                int x = pos[0];
                int y = pos[1];
                
                if (x == itemX && y == itemY){
                    return dis / 2;
                }
                
                for (int d = 0; d < 4; d++){
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    
                    if (nx >= 1 && nx <= 101 && ny >= 1 && ny <= 101){
                        if (!visited[ny][nx] && navigation[ny][nx] == 1){
                            que.offer(new int[] {nx, ny});
                            visited[ny][nx] = true;
                        }
                    }
                }
            }
            dis ++;
        }
                
        return dis;
    }
    
    private void fillOutline(int i, int j, int[][] rectangle, int[][] navigation){
        int minX = rectangle[i][0] * 2;
        int minY = rectangle[i][1] * 2;
        int maxX = rectangle[i][2] * 2;
        int maxY = rectangle[i][3] * 2;
                
        for (int x = minX; x <= maxX; x++) {
            if (navigation[minY][x] != 2) navigation[minY][x] = 1;
            if (navigation[maxY][x] != 2) navigation[maxY][x] = 1;
        }
        for (int y = minY; y <= maxY; y++) {
            if (navigation[y][minX] != 2) navigation[y][minX] = 1;
            if (navigation[y][maxX] != 2) navigation[y][maxX] = 1;
        }
        for (int x = minX + 1; x < maxX; x++) {
            for (int y = minY + 1; y < maxY; y++) {
                navigation[y][x] = 2;
            }
        }
    }
}

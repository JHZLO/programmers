/* 꽉 들어맞는 조각 찾기

1. game_board를 순회하면서 1인 경우 도형의 리스트 집어넣어서 확인할거임

2. table을 순회하면서 1인경우 bfs 돌아서 도형 리스트 저장
- 이때 최대 길이를 기준으로 행렬 생성하고 가져야함

3. 빈공간 리스트랑 도형 비교하면서 끼워맞춰보기 90도씩 4번 회전
*/
import java.util.*;

class Solution {
    private static int[][] move = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    private static int N, M;
    private static boolean[][] visited_table, visited_board;
    private static int[][] board;
    private static int answer = 0;
    
    public int solution(int[][] game_board, int[][] table) {
        board = game_board;
        N = game_board.length;
        M = game_board[0].length;
        
        visited_table = new boolean[N][M];
        visited_board = new boolean[N][M];
        
        for (int i = 0; i < N; i++){
            for (int  j = 0; j < M; j++){
                if (!visited_table[i][j] && table[i][j] == 1){
                    bfs(table, i, j);
                }
            }
        }
        
        return answer;
    }
    private void bfs(int[][] table, int r, int c){
        List<int[]> list = new ArrayList<>();
        
        list.add(new int[]{r,c});
        visited_table[r][c] = true;
        
        int maxR = r;
        int minR = r;
        int maxC = c;
        int minC = c;
        
        int idx = 0;
        while (idx < list.size()){
            int[] pos = list.get(idx++);
            
            for (int i = 0; i < 4; i++){
                int nr = pos[0] + move[i][0];
                int nc = pos[1] + move[i][1];
                
                if (check(nr, nc) && !visited_table[nr][nc] && table[nr][nc] == 1){
                    visited_table[nr][nc] = true;
                    list.add(new int[] {nr, nc});
                    
                    maxR = Math.max(maxR, nr);
                    minR = Math.min(minR, nr);
                    maxC = Math.max(maxC, nc);
                    minC = Math.min(minC, nc);
                }
                
            }
        }
        int[][] arr = makeArr(list, minR, minC, maxR, maxC);
        for (int i = 0; i < 4; i++) {
            if (puzzle(arr, arr.length, arr[0].length)) {
                answer += list.size();
                break;
            }
            if (i != 3) arr = rotate(arr);
        }
    }
    
    private int[][] makeArr(List<int[]> list, int minR, int minC, int maxR, int maxC) {
        int n = maxR - minR + 1; // 배열의 크기 지정인데 딱 맞춰서 설계하려면 +1 해야하므로
        int m = maxC - minC + 1;
        
        int[][] ret = new int[n][m];
        
        for (int[] loc : list) {
            ret[loc[0] - minR][loc[1] - minC] = 1; // 상대적 좌표 {0,0} 기준으로
        }
        
        return ret;
    }
    
    private boolean check(int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= M) return false;
        return true;
    }
    
    private boolean puzzle(int[][] arr, int n, int m) {
        for (int i = 0; i < N - n + 1; i++) {
            for (int j = 0; j < M - m + 1; j++) {
                boolean canPlace = true;  // 퍼즐 배치 가능 여부 체크
                for (int k = 0; k < n && canPlace; k++) {
                    for (int l = 0; l < m && canPlace; l++) {
                        if (arr[k][l] == 1 && 
                            (board[i + k][j + l] == 1 || visited_board[i + k][j + l])) {
                            canPlace = false;  // 퍼즐을 배치할 수 없는 경우 플래그 변경
                        }
                    }
                }
                if (canPlace && checkPuzzle(i, j, arr, n, m)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean checkPuzzle(int startR, int startC, int[][] arr, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j=0;j<m;j++) {
                if (arr[i][j] == 1) {
                    board[startR + i][startC + j] = 1;
                    visited_board[startR + i][startC + j] = true;
                }
            }
        }
        
        boolean flag = false;
        Loop: for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) {
                    for (int k=0;k<4;k++) {
                        int r = startR + i + move[k][0];
                        int c = startC + j + move[k][1];
                        
                        if (!check(r, c)) continue;
                        if (board[r][c] == 0) {
                            flag = true;
                            break Loop;
                        }
                    }
                }
            }
        }
        
        if (flag) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] == 1) {
                        board[startR + i][startC + j] = 0;
                        visited_board[startR + i][startC + j] = false;
                    }
                }
            }
            return false;
        }
        
        return true;
    }
    
     private int[][] rotate(int[][] a) {
        int n = a.length;
        int m = a[0].length;
        
        int[][] ret = new int[m][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ret[j][n - i - 1] = a[i][j]; 
            }
        }
        
        return ret;
    }
}
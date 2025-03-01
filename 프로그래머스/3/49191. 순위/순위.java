/*
좀 애매하다 싶을 때는 일단 그래프로 나타내보기

int[a][b] -> a가 b에게 이겼으면 +1 졌으면 0
플로이드 워셜 적용로이드 워셜 적용
*/
import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int[][] graph = new int[n][n];
        
        for (int[] line: graph){
            Arrays.fill(line, -1);
        }
        
        for (int[] result: results){
            int win = result[0] - 1;
            int lose = result[1] - 1;
            
            graph[win][lose] = 1;
            graph[lose][win] = 0;
        }
        
        for (int i = 0; i < n; i++){
            graph[i][i] = 0;
        }
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                for (int k = 0; k < n; k++){
                    if (graph[j][i] == 1 && graph[i][k] == 1){
                        graph[j][k] = 1;
                        graph[k][j] = 0;
                    }
                }
            }
        }
        
        int cnt = n;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (graph[i][j] < 0){
                    cnt --;
                    break;
                }
            }
        }      
        
        return cnt;
    }
}
/*
1~n까지 배열만들고

    1 2 3 4 5 6 7 8 9 10
    + + + + +            => 2
              + + + + +  => 3
        +       + + + +  => 4
      +     +   +   + +  => 3
        + + + + +        => 3
    1 2 3 2 3 2 4 2 3 3
    
dfs?
만들 수 있는 경우의 수 다 만들고 비교?
*/
import java.util.*;

class Solution {
    private static int answer = 0;
    public int solution(int n, int[][] q, int[] ans) {
         int[] arr = new int[5];
        dfs(q, ans, n, arr, 0, 1); // index 0, 1부터 시작
        
        return answer;
    }
    private void dfs(int[][] q, int[] ans, int n, int[] arr, int len, int x){
        if (len == 5){
            if(check(q, ans, arr)){
                answer ++;
            };
            return;
        }
        
        for (int i = x; i <= n; i ++){
            arr[len] = i;
            dfs(q, ans, n, arr, len+1, i+1);
        }
    }
    private boolean check(int[][] q, int[] ans, int[] arr){
        Set<Integer> dic = new HashSet<>();
        
        for (int i = 0; i < arr.length; i++){
            dic.add(arr[i]);
        }
        
        for (int i = 0; i < q.length; i++){
            int cnt = 0;
            for (int j = 0; j < 5; j++){
                if (dic.contains(q[i][j])){
                    cnt ++;
                }
            }
            if (cnt != ans[i]){
                return false;
            }
        }
        return true;
    }
}
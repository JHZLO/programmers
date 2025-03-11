import java.util.*;

// class Solution {
//     private static int cnt = 0;
    
//     public int solution(int n, int[] money) {
//         dfs(money, 0, n);
//         return cnt;
//     }
//     private void dfs(int[] money, int sum, int n){
//         if (sum == n){
//             cnt ++;
//             return;
//         }else if (sum > n){
//             return;
//         }
        
//         for (int i = 0; i < money.length; i++){
//             sum += money[i];
//             dfs(money, sum, n);
//         }
//     }
// }

class Solution {
    static final long INF = 1000000007;
    public long solution(int n, int[] money) {
        int size = money.length;
        long [] DP = new long[n+1];
        for(int i = 0; i < size; i++){
            int cur = money[i];
            if(cur > n) continue;
            DP[cur]++;
            for(int j = 1; j <= n; j++){
                if(j - cur >= 0){
                    DP[j] += DP[j-cur];
                    DP[j] %= INF;
                }
            }
        }
        return DP[n];
    }
}
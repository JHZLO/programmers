/*
집이 있는 곳의 좌표는 (1, 1)로 나타내고 가장 오른쪽 아래, 즉 학교가 있는 곳의 좌표는 (m, n)으로 나타낸다
puddles -> 지나갈 수 없는 곳

쉬운데?
<출력>
최단 경로의 개수 % 1,000,000,0007?
*/

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n][m];

        for (int[] puddle : puddles) {
            dp[puddle[1] - 1][puddle[0] - 1] = -1;
        }

        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] == -1) { 
                    dp[i][j] = 0;
                    continue;
                }

                if (i > 0) dp[i][j] += dp[i-1][j];  
                if (j > 0) dp[i][j] += dp[i][j-1];  

                dp[i][j] %= 1000000007; 
            }
        }

        return dp[n-1][m-1];
    }
}

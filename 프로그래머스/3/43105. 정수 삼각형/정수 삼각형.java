/*
<조건>
- 아래 칸으로 이동할 때는 대각선 방향으로 한 칸 오른쪽 또는 왼쪽으로만 이동 가능
    - 현재 위치 index or 현재 위치 index + 1

1번째 dp[1]
- 7

2번째 dp[2]
- 7 + 3
- 7 + 8

3번째 dp[3]
- 7 + 3 + 8
- 7 + 3 + 1
- 7 + 8 + 1
- 7 + 8 + 0

index = 0
a = Math.max(dp[i-2] + arr[i-1][index] + arr[i][index], dp[i-2] + arr[i-1][index] + arr[i][index+1])

index = 1
b = Math.max(dp[i-2] + arr[i-1][index] + arr[i][index], dp[i-2] + arr[i-1][index] + arr[i][index+1])

if (a < b){  // 같은 경우는 어떻게 하지?
index ++
}
dp[i] = b
dp[i-1] = dp[i-2] + arr[i-1][index]

<출력>
삼각형의 정보가 담긴 배열 triangle이 매개변수로 주어질 때, 거쳐간 숫자의 최댓값을 return


제한사항
삼각형의 높이는 1 이상 500 이하입니다.
삼각형을 이루고 있는 숫자는 0 이상 9,999 이하의 정수입니다.
*/
import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
//         int[] dp = new int[501];
//         int height = triangle.length;
        
//         dp[1] = triangle[0][0];
        
//         if (height == 2){
//            return Math.max(dp[1] + triangle[1][0], dp[1] + triangle[1][1]);
//         }
        
//         int index = 0; // index가 0부터 시작하니까
//         for (int i = 3; i <= height; i ++){
//             int a = Math.max(dp[i-2] + triangle[i-2][index] + triangle[i-1][index],
//                              dp[i-2] + triangle[i-2][index] + triangle[i-1][index+1]);
//             int b =  Math.max(dp[i-2] + triangle[i-2][index+1] + triangle[i-1][index+1], 
//                               dp[i-2] + triangle[i-2][index+1] + triangle[i-1][index+2]);
            
//             if (a < b){
//                 dp[i] = b;
//                 index ++;
//             }else{
//                 dp[i] = a;
//             }
//             dp[i-1] = dp[i-2] + triangle[i-2][index];   
//         }
//         return dp[height]; int height = triangle.length;
        int height = triangle.length;
        int[][] dp = new int[height][height];

        // DP 배열 초기화 (맨 아래 줄 그대로 복사)
        for (int i = 0; i < height; i++) {
            dp[height-1][i] = triangle[height-1][i];
        }

        // Bottom-Up 방식으로 위로 올라가면서 최댓값 계산
        for (int i = height - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = triangle[i][j] + Math.max(dp[i+1][j], dp[i+1][j+1]);
            }
        }

        return dp[0][0];
    }
}
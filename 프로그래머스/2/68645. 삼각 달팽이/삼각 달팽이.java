/*
[1]
[2,9]
[3,10,8]
[4,5,6,7]
=> (+1, 0), (0, +1), (-1, -1) 이렇게 세 방향으로 움직이면서 숫자 증가
x의 인덱스 끝에 다다르면 (0, +1)
y의 인덱스 끝에 다다르면 (+1, 0)
x인덱스, y인덱스 끝에 다다르면 (-1, -1)
(visited를 기준도 고려하면서)
*/
import java.util.*;

import java.util.*;

class Solution {
    private static final int[] dx = {0, 1, -1}; // 아래, 오른쪽, 대각선 위
    private static final int[] dy = {1, 0, -1}; // 아래, 오른쪽, 대각선 위

    public int[] solution(int n) {
        int[][] triangle = new int[n][n];

        // 삼각형을 -1로 초기화
        for (int[] row : triangle) {
            Arrays.fill(row, -1);
        }

        int x = 0, y = 0, num = 1, direction = 0;
        triangle[y][x] = num;

        while (num < (n * (n + 1)) / 2) {
            int ny = y + dy[direction];
            int nx = x + dx[direction];

            // 범위를 벗어나거나 이미 채워진 칸이면 방향 변경
            if (ny < 0 || nx < 0 || ny >= n || nx >= n || triangle[ny][nx] != -1) {
                direction = (direction + 1) % 3; // 방향 변경
                ny = y + dy[direction];
                nx = x + dx[direction];
            }

            // 숫자 채우기
            num++;
            triangle[ny][nx] = num;
            y = ny;
            x = nx;
        }

        // 결과를 1차원 배열로 변환
        int[] result = new int [ n * (n+1) / 2];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                result[index] = triangle[i][j];
                index ++;
            }
        }

        return result;
    }
}

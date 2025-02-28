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

class Solution {
    private static final int[] dx = {0, 1, -1}; // 아래, 오른쪽, 대각선 위
    private static final int[] dy = {1, 0, -1}; // 아래, 오른쪽, 대각선 위

    public int[] solution(int n) {
        int[][] triangle = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        // 삼각형을 채울 배열 생성
        for (int[] row : triangle) {
            Arrays.fill(row, -1); // 초기값을 -1로 설정
        }

        int x = 0, y = 0, num = 1, direction = 0; // 초기 위치 (0,0) 시작, 방향 index = 0
        triangle[y][x] = num;
        visited[y][x] = true;

        while (num < (n * (n + 1)) / 2) { // 삼각형 숫자 개수만큼 반복
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if (nx >= 0 && ny >= 0 && nx < n && ny < n && triangle[ny][nx] == -1 && !visited[ny][nx]) {
                // 이동 가능하면 이동
                x = nx;
                y = ny;
                num++;
                triangle[y][x] = num;
                visited[y][x] = true;
            } else {
                // 이동 불가능하면 방향 변경
                direction = (direction + 1) % 3;
            }
        }

        // 결과를 1차원 배열로 변환
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                result.add(triangle[i][j]);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}

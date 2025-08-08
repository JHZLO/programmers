/*
31464 초콜릿 괴도 코코

<1단계>
- NxN의 격자 형태 초콜릿 -> 1x1 단위로 원하는 곳에서 뗄 수 있음 -> 처음 주어질 때 0개 이상의 단위초콜릿이 제거된 상태
- 이때, 남은 단위 초콜릿은 4개 이상 -> 서로 연결되어있어야함 (하나의 조각)

<2단계>
- 먼 소리임? ㅋㅋ
- 하나의 단위 초콜렛을 떼는데 이걸 뗀 이후에 하나만 제거했을 때 두 조각으로 나뉘어야함

<입력>
첫번째 줄: N
# : 초콜릿
. : 없음

<출력>
2단계의 조건을 충족하도록 뗴어낼 수 있ㅇ는 서로 다른 초콜릿의 개수 -> 그리고 행과 열 차례로 출력

 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static char[][] g;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        g = new char[N][N];
        int total = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine().trim(); // 공백 없음
            for (int j = 0; j < N; j++) {
                g[i][j] = line.charAt(j);
                if (g[i][j] == '#') total++;
            }
        }

        List<int[]> ans = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (g[r][c] != '#') continue;

                g[r][c] = '.';
                if (isTreeAfterRemoval(total - 1)) {
                    ans.add(new int[]{r + 1, c + 1});
                }
                g[r][c] = '#';
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans.size()).append('\n');
        for (int[] pos : ans) {
            sb.append(pos[0]).append(' ').append(pos[1]).append('\n');
        }
        System.out.print(sb);
    }

    static boolean isTreeAfterRemoval(int remain) {
        if (remain <= 0) return false;

        boolean[][] vis = new boolean[N][N];
        Deque<int[]> dq = new ArrayDeque<>();

        // 시작점 찾기
        outer:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (g[i][j] == '#') {
                    dq.add(new int[]{i, j});
                    vis[i][j] = true;
                    break outer;
                }
            }
        }

        int V = 0;
        int edgeTwice = 0; // 간선을 양 방향으로 세서 나중에 /2

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int r = cur[0], c = cur[1];
            V++;

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k], nc = c + dc[k];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (g[nr][nc] != '#') continue;

                edgeTwice++; // (r,c)-(nr,nc) 인접 간선 카운트

                if (!vis[nr][nc]) {
                    vis[nr][nc] = true;
                    dq.add(new int[]{nr, nc});
                }
            }
        }

        if (V != remain) return false;

        int E = edgeTwice / 2;
        return E == V - 1;
    }
}

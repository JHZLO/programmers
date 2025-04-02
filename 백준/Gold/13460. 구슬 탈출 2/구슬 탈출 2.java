import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static boolean[][][][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class State {
        int rx, ry, bx, by, depth;
        State(int rx, int ry, int bx, int by, int depth) {
            this.rx = rx; this.ry = ry;
            this.bx = bx; this.by = by;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        int rx=0, ry=0, bx=0, by=0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') { rx = i; ry = j; }
                if (board[i][j] == 'B') { bx = i; by = j; }
            }
        }

        System.out.println(bfs(rx, ry, bx, by));
    }

    private static int bfs(int rx, int ry, int bx, int by) {
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(rx, ry, bx, by, 0));
        visited[rx][ry][bx][by] = true;

        while (!queue.isEmpty()) {
            State cur = queue.poll();
            if (cur.depth >= 10) return -1;

            for (int d = 0; d < 4; d++) {
                int[] r = move(cur.rx, cur.ry, dx[d], dy[d]);
                int[] b = move(cur.bx, cur.by, dx[d], dy[d]);

                int nrx = r[0], nry = r[1], rc = r[2];
                int nbx = b[0], nby = b[1], bc = b[2];

                if (board[nbx][nby] == 'O') continue; // 파랑 빠지면 무효
                if (board[nrx][nry] == 'O') return cur.depth + 1; // 빨강만 빠짐

                // 같은 위치에 멈췄으면 → 더 멀리 이동한 구슬을 뒤로 한 칸
                if (nrx == nbx && nry == nby) {
                    if (rc > bc) {
                        nrx -= dx[d];
                        nry -= dy[d];
                    } else {
                        nbx -= dx[d];
                        nby -= dy[d];
                    }
                }

                if (!visited[nrx][nry][nbx][nby]) {
                    visited[nrx][nry][nbx][nby] = true;
                    queue.offer(new State(nrx, nry, nbx, nby, cur.depth + 1));
                }
            }
        }

        return -1;
    }

    // 공을 끝까지 굴리는 함수
    private static int[] move(int x, int y, int dx, int dy) {
        int cnt = 0;
        while (board[x + dx][y + dy] != '#' && board[x][y] != 'O') {
            x += dx;
            y += dy;
            cnt++;
        }
        return new int[]{x, y, cnt};
    }
}

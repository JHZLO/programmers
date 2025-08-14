/*
17265

 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int min = Integer.MAX_VALUE;
    public static int max = Integer.MIN_VALUE;
    public static int[] dx = {1, 0};
    public static int[] dy = {0, 1};
    public static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[][] arr = new String[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = st.nextToken();
            }
        }

        dfs(0, 0, Integer.parseInt(arr[0][0]), arr[0][0], arr);
        System.out.println(max + " " + min);
    }

    public static void dfs(int x, int y, int ans, String what, String[][] arr) {
        for (int i = 0; i < 2; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < N && ny < N) {
                if (arr[nx][ny].equals("*")) {
                    dfs(nx, ny, ans, "*", arr);
                } else if (arr[nx][ny].equals("+")) {
                    dfs(nx, ny, ans, "+", arr);
                } else if (arr[nx][ny].equals("-")) {
                    dfs(nx, ny, ans, "-", arr);
                } else {
                    int result = 0;

                    if(what.equals("+")) {
                        result = ans + Integer.parseInt(arr[nx][ny]);
                    } else if(what.equals("-")) {
                        result = ans - Integer.parseInt(arr[nx][ny]);
                    } else if(what.equals("*")) {
                        result = ans * Integer.parseInt(arr[nx][ny]);
                    }

                    if(nx == N - 1 && ny ==  N - 1) {
                        max = Math.max(max, result);
                        min = Math.min(min, result);

                        return;
                    }

                    dfs(nx, ny, result, arr[nx][ny], arr);
                }
            }
        }
    }
}

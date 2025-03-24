import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 21736 헌내기는 친구가 필요해
public class Main {
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[][] campus = new String[n][m];
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                campus[i][j] = line.substring(j,j+1);
                if (campus[i][j].equals("I")) {
                    que.offer(new int[]{i, j});
                }
            }
        }

        int count = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++){
                int[] pos = que.poll();
                int x = pos[1];
                int y = pos[0];

                if (campus[y][x].equals("P")){
                    count += 1;
                }

                for (int j = 0; j < 4; j++){
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if (nx >= 0 && ny >= 0 && nx < m && ny < n){
                        if (!visited[ny][nx] && !campus[ny][nx].equals("X")){
                            que.offer(new int[] {ny, nx});
                            visited[ny][nx] = true;
                        }
                    }
                }
            }
        }

        if (count == 0){
            System.out.println("TT");
        }else{
            System.out.println(count);
        }
    }
}

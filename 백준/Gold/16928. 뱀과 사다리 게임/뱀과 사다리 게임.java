import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 16928 뱀과 사다리 게임
public class Main {
    private static boolean[] visited = new boolean[101];
    private static int min_cnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] ladder_snake = new int[101];
        Arrays.fill(ladder_snake, 0);

        String[] input = br.readLine().split(" ");
        int ladder_cnt = Integer.parseInt(input[0]);
        int snake_cnt = Integer.parseInt(input[1]);

        for (int i = 0; i < ladder_cnt + snake_cnt; i++) {
            String[] pos = br.readLine().split(" ");
            int from = Integer.parseInt(pos[0]);
            int to = Integer.parseInt(pos[1]);
            ladder_snake[from] = to;
        }

        bfs(ladder_snake);

        System.out.println(min_cnt);
    }

    private static void bfs(int[] ladder_snake) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(1);
        visited[1] = true;

        while (!que.isEmpty()) {
            min_cnt++;
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int index = que.poll();
                for (int j = 1; j <= 6; j++) {
                    int new_index = index + j;

                    if (new_index == 100) {
                        return;
                    }

                    if (!visited[new_index] && new_index <= 100 && new_index > 0) {
                        visited[new_index] = true; // 똑같은 곳을 또 방문했다는건 돌아서 왔다는거니까 제외
                        if (ladder_snake[new_index] != 0) {
                            new_index = ladder_snake[new_index];
                        }
                        visited[new_index] = true;
                        que.offer(new_index);
                    }
                }
            }
        }
    }
}

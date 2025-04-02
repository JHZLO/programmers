import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
모든 정점들을 연결하는 부분 그래프 중에서 가중치의 합이 최소인 트리
*/

// 1197 최소 스패닝 트리
public class Main {
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        Map<Integer, List<int[]>> tree = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            tree.put(i, new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st2.nextToken());
            int to = Integer.parseInt(st2.nextToken());
            int cost = Integer.parseInt(st2.nextToken());

            tree.get(from).add(new int[]{to, cost});
            tree.get(to).add(new int[]{from, cost});
        }

        System.out.println(prim(tree));
    }

    private static int prim(Map<Integer, List<int[]>> tree) {
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e[1]));

        pq.offer(new int[]{1, 0});
        int totalCost = 0;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int node = now[0];
            int cost = now[1];

            if (visited[node]) continue;

            visited[node] = true;
            totalCost += cost;

            for (int[] next : tree.get(node)) {
                if (!visited[next[0]]) {
                    pq.offer(new int[]{next[0], next[1]});
                }
            }
        }

        return totalCost;
    }
}

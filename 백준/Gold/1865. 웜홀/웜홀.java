import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
/*
N개의 지점 사이에는 M개의 도로와 W개의 웜홀이 있다.
- 도로는 방향 x, 웜홀은 방향 o
웜홀 : 시작 위치 ~ 도착 위치로 가는 하나의 경로
- 도착을 하게 되면 시작을 하였을 때보다 시간이 뒤로 가게된다.

한 지점에서 출발을 하여서 시간여행을 하기 시작하여 다시 출발을 하였던 위치로 돌아왔을 떄,
출발을 하였을 때보다 시간이 되돌아가는 경우가 있을까?
=> 출력: YES or NO

풀이: 밸만 포드 알고리즘
(정점의 개수 - 1)번 동안 그래프 내의 모든 간선을 보면서 최단거리 배열(dist)를 계속 업데이트를 하는 작업

모든 정점을 출발점으로 삼고, 벨만포드 알고리즘을 그대로 구현

*** 모든 간선을 v-1번 반복하면 최단 거리를 업데이트할 수 있음
but, V번째에도 거리가 갱신된다? => 음수 사이클이 발생했다는 뜻! ***
*/

// 1865 웜홀
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int TC_COUNT = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TC_COUNT; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 지점 수
            int M = Integer.parseInt(st.nextToken()); // 도로 수
            int W = Integer.parseInt(st.nextToken()); // 웜홀 수

            List<int[]> edges = new ArrayList<>();

            // 도로 (양방향)
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                edges.add(new int[]{from, to, cost});
                edges.add(new int[]{to, from, cost});
            }

            // 웜홀 (단방향, 시간 되돌리므로 음수 가중치)
            for (int j = 0; j < W; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                edges.add(new int[]{from, to, -time});
            }

            // 벨만 포드로 음수 사이클 존재 확인
            boolean hasCycle = bellmanFord(N, edges);
            sb.append(hasCycle ? "YES\n" : "NO\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean bellmanFord(int n, List<int[]> edges) {
        int[] dist = new int[n + 1];

        for (int start = 1; start <= n; start++) {
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[start] = 0;

            boolean updated = false;

            for (int i = 1; i <= n; i++) {
                updated = false;
                for (int[] edge : edges) {
                    int from = edge[0];
                    int to = edge[1];
                    int cost = edge[2];

                    if (dist[from] != Integer.MAX_VALUE && dist[to] > dist[from] + cost) {
                        dist[to] = dist[from] + cost;
                        updated = true;

                        // 마지막 반복에서도 업데이트 발생 시 -> 음수 사이클
                        if (i == n) return true;
                    }
                }
                if (!updated) break;
            }
        }

        return false; // 음수 사이클 없음
    }
}

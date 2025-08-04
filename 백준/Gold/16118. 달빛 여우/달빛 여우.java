/*
<문제 조건>
- 달빛 늑대와 달빛 여우는 1번 나무 그루터기에서  살고 있음
- 달빛 늑대는 달빛 여우보다 빠르지만  체력이 적음
    - 달빛 늑대가 달빛 여우보다 2배 더 빠름 -> 0.5배 느림 -> 2배빠름 반복

달빛 여우가 달빛 늑대보다 먼저 도착할 수 있도록 그루터기에 달빛을 비춰줄것임 -> 달빛 여우가 달빛 늑대보다 먼저 도착할 수 있는 나무 그루터기의 개수

N, M : 나무 그루터기 개수, 오솔길 개수
M개의 줄 (from, to, dis)
*/

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

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, List<int[]>> map = new HashMap<>();

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        for (int i = 1; i <= N; i++) {
            map.putIfAbsent(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            map.get(from).add(new int[]{to, dis * 2});
            map.get(to).add(new int[]{from, dis * 2});
        }

        int[] foxDis = new int[N + 1];
        Arrays.fill(foxDis, Integer.MAX_VALUE);
        foxDis[1] = 0;

        int[] renewedFoxDis = foxDijkstra(map, foxDis, N);
        int[][] renewedWolfDis = wolfDijkstra(map, N);

        int count = 0;
        for (int i = 2; i <= N; i++) {
            int wolfMin = Math.min(renewedWolfDis[i][0], renewedWolfDis[i][1]);
            if (renewedFoxDis[i] < wolfMin) {
                count++;
            }
        }

        System.out.println(count);
        br.close();
    }

    private static int[] foxDijkstra(Map<Integer, List<int[]>> map, int[] disArr, int N) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));
        pq.offer(new int[]{1, 0});

        while (!pq.isEmpty()) {
            int[] x = pq.poll();
            int pos = x[0];
            int dis = x[1];

            if (disArr[pos] < dis) {
                continue;
            }

            for (int[] road : map.get(pos)) {
                int newPos = road[0];
                int cost = road[1];

                if (dis + cost < disArr[newPos]) {
                    disArr[newPos] = dis + cost;
                    pq.offer(new int[]{newPos, disArr[newPos]});
                }
            }
        }

        return disArr;
    }

    private static int[][] wolfDijkstra(Map<Integer, List<int[]>> map, int N) {
        int[][] dist = new int[N + 1][2];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));
        dist[1][0] = 0;
        pq.offer(new int[]{1, 0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int pos = cur[0];
            int time = cur[1];
            int type = cur[2];

            if (dist[pos][type] < time) continue;

            for (int[] next : map.get(pos)) {
                int to = next[0];
                int edgeCost = next[1];

                int nextType = 1 - type;
                int nextTime = type == 0 ? time + edgeCost / 2 : time + edgeCost * 2;

                if (dist[to][nextType] > nextTime) {
                    dist[to][nextType] = nextTime;
                    pq.offer(new int[]{to, nextTime, nextType});
                }
            }
        }

        return dist;
    }
}

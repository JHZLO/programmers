/*
하나의 지점에서 A, B, S 까지의 최단 거리
dijkstra 알고리즘 적용
distA, distB, distS의 각각의 index에 대한 거리의 합의 최솟값 구하기
*/
import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        
        for (int i = 1; i <= n; i ++){
            graph.put(i, new ArrayList<>());
        }
        
        for (int i = 0; i < fares.length; i++){
            int from = fares[i][0];
            int to = fares[i][1];
            int cost = fares[i][2];
            
            graph.get(from).add(new int[] {to, cost});
            graph.get(to).add(new int[] {from, cost});
        }
        
        int[] distS = dijkstra(n, s, graph);
        int[] distA = dijkstra(n, a, graph);
        int[] distB = dijkstra(n, b, graph);
        
        int min_cost = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++){
            if (distS[i] == Integer.MAX_VALUE || distA[i] == Integer.MAX_VALUE || distB[i] == Integer.MAX_VALUE){
                continue;
            }
            min_cost = Math.min(min_cost, distS[i] + distA[i] + distB[i]);
        }
        
        return min_cost;
    }
    
    private int[] dijkstra(int n, int start, Map<Integer, List<int[]>> graph){
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s[1]));
        pq.offer(new int[] {start, 0});
        dist[start] = 0;
        
        while (!pq.isEmpty()){
            int[] pos = pq.poll();
            int idx = pos[0];
            int cost = pos[1];
            
            if (dist[idx] < cost){
                continue;
            }
            
            for (int[] node: graph.get(idx)){
                int distance = cost + node[1];
                if (distance < dist[node[0]]){
                    dist[node[0]] = distance;
                    pq.offer(new int[] {node[0], distance});
                }
            }
        }
        
        return dist;
    }
}
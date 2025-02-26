/*
s, a, b에서 목적지 한 곳 찍고 가장 간선 합이 적은 위치..
*/

import java.util.*;

class Solution {
    class Node {
        int idx;
        int cost;
        
        public Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }
    }
    public int solution(int n, int s, int a, int b, int[][] fares) {
        Map<Integer, List<Node>> graph = new HashMap<>();
        
        for (int i = 1; i <= n; i ++){
            graph.put(i, new ArrayList<>());
        }
        
        for (int[] fare: fares){
            int from = fare[0];
            int to = fare[1];
            int cost = fare[2];
            
            graph.get(from).add(new Node(to, cost));
            graph.get(to).add(new Node(from, cost));
        }
        
        // 다익스트라 실행 (각 출발점에서 모든 거리 구하기)
        int[] distS = dijkstra(n, s, graph); // S에서 출발하는 최단 거리
        int[] distA = dijkstra(n, a, graph); // A에서 출발하는 최단 거리
        int[] distB = dijkstra(n, b, graph); // B에서 출발하는 최단 거리
        
        int min_dist = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++){
            if (distS[i] == Integer.MAX_VALUE || distA[i] == Integer.MAX_VALUE || distB[i] == Integer.MAX_VALUE){
               continue; // 연결되어 있지 않음을 맨처음에 채워놓은 값이랑 같을 때로 비교 
            }
            min_dist = Math.min(min_dist, distS[i] + distA[i] + distB[i]);
        }
        
        return min_dist;
    }
    
    private int[] dijkstra(int n, int start, Map<Integer, List<Node>> graph){
        // cost 비용 적은 순대로 오름차순 하는 heap 생성
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        int[] dist = new int[n+1]; // 각 위치에 대한 최소 거리를 저장하는 배열
        
        Arrays.fill(dist, Integer.MAX_VALUE); // 최소값으로 업데이트해야하므로 max값 채워놓기
        
        pq.offer(new Node(start,0)); // 시작 지점 삽입
        dist[start] = 0;
        
        int total_dist = 0;
        while (!pq.isEmpty()){
            Node node = pq.poll();
            int curr = node.idx;
            int cost = node.cost;
            
            if (dist[curr] < cost){
                continue;
            }
            
            for (Node next: graph.get(curr)){
                int new_dist = next.cost + cost;
                if (new_dist < dist[next.idx]){
                    dist[next.idx] = new_dist;
                    pq.offer(new Node(next.idx, new_dist));
                }
            }
        }
        return dist;
    }
}
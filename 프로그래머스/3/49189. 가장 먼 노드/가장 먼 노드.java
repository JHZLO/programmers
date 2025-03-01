import java.util.*;

class Solution {
    class Node{
        int index;
        int cost;
        
        public Node(int index, int cost){
            this.index = index;
            this.cost = cost;
        }
    }
    public int solution(int n, int[][] edge) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        PriorityQueue<int[]> que = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));
        
        for (int i = 1; i <= n; i++){
            graph.put(i, new ArrayList<>());
        }
        
        for (int[] vertex: edge){
            int from = vertex[0];
            int to = vertex[1];
            
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        
        int[] dist = new int[n + 1]; // 1번 노드에서 각 노드까지의 거리를 측정
        Arrays.fill(dist, Integer.MAX_VALUE);
        que.offer(new int[] {1, 0}); // index, distance
        
        while (!que.isEmpty()){
            int[] node = que.poll();
            int index = node[0];
            int distance = node[1];
            
            if (dist[index] < distance){
                continue;
            }
            
            for (int x: graph.get(index)){
                int new_dist = distance + 1;
                if (dist[x] > new_dist){
                    dist[x] = new_dist;
                    que.offer(new int[] {x, dist[x]});
                }
            }
        }
        
        int max_distance = 0;
        for (int i = 2; i < dist.length; i++){
            max_distance = Math.max(max_distance, dist[i]);
        }
        
        int answer = 0;
        for (int i = 2; i < dist.length; i++){
            if (dist[i] == max_distance){
                answer ++;
            }
        }
        
        return answer;
    }
}
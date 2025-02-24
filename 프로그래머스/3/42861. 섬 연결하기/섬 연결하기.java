/*
<조건>
- 같은 연결은 두번 주어지지 않는다. (순서가 바뀌더라도 같은 연결)
- 섬 사이의 다리 건설 비용이 주어지지 않는 경우 두 섬 사이 건설 불가

<입력>
costs[i][0] costs[i][1] : 다리가 연결되는 두 섬의 번호
costs[i][2]: 다리를 건설할 때 드는 비용

<출력>
최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용을 return 

Priority Queue를 적용해서 cost가 낮은 값 부터 큐에 넣기
[0 : [[key, value], [key,value] , ...]
*/
import java.util.*;

class Solution {
    class Pair {
        int index;
        int weight;
        
        public Pair(int index, int weight){
            this.index = index;
            this.weight = weight;
        }
    }
    
    public int solution(int n, int[][] costs) {
        Map<Integer, List<Pair>> graph = new HashMap<>();
        
        for (int i = 0; i < n; i++){
            graph.put(i, new ArrayList<>());
        }
        
        for (int i = 0; i < costs.length; i++){
            int from = costs[i][0];
            int to = costs[i][1];
            int weight = costs[i][2];
            
            graph.get(from).add(new Pair(to, weight));
            graph.get(to).add(new Pair(from, weight));
        }
        
        boolean[] visited = new boolean[n];
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(pair -> pair.weight));
        pq.offer(new Pair(0,0)); // to, weight
        
        int distance = 0;
        while (!pq.isEmpty()){
            Pair pair = pq.poll();
            int from = pair.index; // 시작점이였는데 다시 출발점
            int weight = pair.weight;
            
            
            if (visited[from]){
                continue; // 큐에 남아있는 것들 중 최소 거리만 딱 찍고 다음꺼 가야지
            }
            
            distance += weight;
            visited[from] = true;
            
            for (Pair x : graph.get(from)){
                if (!visited[x.index]){
                    pq.offer(new Pair(x.index, x.weight));
                }
            }
            
            boolean is_not_break = false;
            for (int i = 0; i < n; i++){
                if (visited[i]){
                    is_not_break = true;
                }
            }
            if (!is_not_break){
                break;
            }
        }
        
        return distance;
    }
}
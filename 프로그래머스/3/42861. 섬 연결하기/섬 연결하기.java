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
*/
import java.util.*;

class Solution {
    class Pair<K, V> {
        K key;
        V value;
        
        public Pair(K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    
    public int solution(int n, int[][] costs) {
        // 시작 지점 노드: 도착 지점 노드, costs
        Map<Integer, List<Pair<Integer, Integer>>> graph = new HashMap<>();
        for (int i = 0; i < n; i++){
            graph.put(i, new ArrayList<>());
        }
        
        // 양방향 그래프 구성하기
        for (int[] cost: costs){
            int from = cost[0];
            int to = cost[1];
            int weight = cost[2];
            
            graph.get(from).add(new Pair<>(to, weight));
            graph.get(to).add(new Pair<>(from,weight));
        }
        
        PriorityQueue<Pair<Integer,Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.value));
        Set<Integer> visited = new HashSet<>();
        
        pq.offer(new Pair<>(0,0)); // 시작점, 비용
        int totalCost = 0;
        
        while (!pq.isEmpty()){
            Pair<Integer, Integer> pair = pq.poll();
            int to = pair.key;
            int weight = pair.value;
            
            if (visited.contains(to)){
                continue;
            }
            
            visited.add(to);
            totalCost += weight;
            
            for (Pair<Integer, Integer> child : graph.get(to)){
                if (!visited.contains(child.key)){
                    pq.offer(new Pair<>(child.key, child.value));
                }
            }
            
            if (visited.size() == n){
                break;
            }
        }
        
        return totalCost;
    }
}
/*
- 모든 트럭이 다리를 건널려면 최소 몇 초가 걸리는지?

[풀이]
- 먼저 들어온 애가 먼저 나갈 수 있는 자료구조 -> Queue : 크기 bridge_length
- 차례대로 트럭이 올라감
    - 다음 트럭이 올라올 수 있으면 큐에 집어넣고
    - 없으면 0 집어넣음
    
    - 큐 사이즈가 bridge_length면 하나 빼고
*/
import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int bridge_weight = 0;
        int time = 0;
        
        Queue<Integer> que = new LinkedList<>();
        
        for (int i = 0; i < truck_weights.length; i++){
            while (true){
                if (que.size() == bridge_length){
                    bridge_weight -= que.poll();
                }else{
                    if (bridge_weight + truck_weights[i] <= weight){
                        que.offer(truck_weights[i]);
                        bridge_weight += truck_weights[i];
                        time ++;
                        break;
                    }else{
                        que.offer(0);
                        time ++;    
                    }
                }
            }
        }
        
        
        return time + bridge_length;
    }
}
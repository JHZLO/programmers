import java.util.*;

/*
문제 조건
- 트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 합니다.
- 다리에는 bridge length만큼의 트럭이 올라갈 수 있음
- 다리는 weigth 이하의 무게만 견딜 수 있음

문제 풀이
- 다리의 길이 만큼의 큐를 둔다.
- 큐에는 트럭의 무게가 차례대로 들어간다.
    - 큐가 비어있다면 트럭이 들어간다 (무조건)
    - 다리의 길이만큼 트럭이 들어온 경우
        - 다리 큐에서 트럭의 무게를 뺀다.
    - 다리의 길이만큼 트럭이 들어오지 않은 경우
        - 트럭이 추가로 들어올 수 있는지 확인한다.
            - 추가로 들어올 수 있으면 큐에 삽입하고 총 무게를 더한다.
            - 추가로 들어올 수 없으면 큐에 빈 값(0)을 삽입하여 기존의 트럭이 이동하도록 한다.
 */

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();

        int bridge_weigth = 0;
        int time = 0;

        for (int i = 0; i < truck_weights.length; i++) {
            while (true){
                if (bridge.isEmpty()){
                   bridge.add(truck_weights[i]);
                   bridge_weigth += truck_weights[i];
                   time ++;
                   break;
                }
                // bridge에 트럭이 전부 올라갔는지에 대한 검증이 우선 필요
                if (bridge.size() == bridge_length){
                    bridge_weigth -= bridge.poll();
                } else { // 다리가 비어있다면
                    if (truck_weights[i] + bridge_weigth <= weight){
                        bridge.add(truck_weights[i]);
                        bridge_weigth += truck_weights[i];
                        time ++;
                        break;
                    } else {
                        bridge.add(0); // 기존에 있는 트럭 이동하도록 함
                        time ++;
                    }
                }
            }
        }

        return time + bridge_length;
    }
}

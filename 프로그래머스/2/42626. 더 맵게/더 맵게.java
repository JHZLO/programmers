/*
- 우선순위 큐 활용
- scovile 오름차순 정렬
    - 1. 0번째 인덱스 + (1번째 인덱스 * 2) 한 값 큐에 다시 집어넣기
    - 2. 기존의 0, 1 번째 인덱스는 pop
    - 3. cnt ++
    - 4. 1번째 scovile의 값이 7이 넘으면 순회 종료 -> cnt return
*/
import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        Queue<Integer> sortedScoville = new PriorityQueue<>();
        
        for (int i = 0; i < scoville.length; i++){
            sortedScoville.add(scoville[i]);
        }
        
        int cnt = 0;
        
        while (!sortedScoville.isEmpty()){
            int a = sortedScoville.poll();
            if (a >= K){
                break;
            }
            if (sortedScoville.isEmpty()){
                return -1;
            }
            
            int b = sortedScoville.poll();
            
            sortedScoville.add(a + (b * 2));
            cnt ++;
        }
        
        return cnt;
    }
}
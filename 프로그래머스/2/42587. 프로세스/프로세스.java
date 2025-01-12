import java.util.*;

/*
기존 배열의 index와 큐와 어떻게 비교할까

문제해결
1. 우선순위 큐 생성
2. 우선순위 큐에 priorities 배열의 값 add
3. 우선순위 큐를 empty될 때 까지 순회하면서 내부에 for문으로 priorities 순회
4. 이때, priorities[i]값과 큐의 peak값이 같은지 확인
    4-1. 만약 같은 경우 i값과 location값이 같은지 비교 -> 같으면 해당 count값 return
    4-2. 같지 않다면 count ++
*/

class Solution {
    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> processes = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare (Integer o1, Integer o2){
                return o2 - o1;
            }
        });
        
        for (int i : priorities){
            processes.offer(i);
        }
        
        int cnt = 0;
        while (!processes.isEmpty()) {
            for (int i = 0; i < priorities.length; i++){
                if (processes.peek() == priorities[i]){
                    processes.remove();
                    cnt ++;
                    if (i == location){
                        return cnt;
                    }
                }
            }
		}
        
        return cnt;
    }
}
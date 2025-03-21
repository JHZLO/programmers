/*
- 모든 음식의 스코빌 지수를 K 이상으로 만들고자함
섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
=> 모든 음식의 스코빌 지수가 K 이상이 될 때 까지 반복하여 섞음

*/
import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i: scoville){
            pq.offer(i);
        }
        
        while(true){
            int x = pq.poll();
            if (x >= K){
                break;
            }else{
                if (pq.isEmpty()){
                    return -1;
                }
                int y = pq.poll();
                int sc = x + y * 2;
                pq.offer(sc);
                answer ++;
            }
        }
        
        return answer;
    }
}
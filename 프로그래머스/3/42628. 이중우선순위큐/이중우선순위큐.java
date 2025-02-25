/*
- minHeap과 maxHeap을 따로따로 만들어야 할까?
- 그리고 Insert 연산에 대해서는 두 개 다?
*/
import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> min_pq = new PriorityQueue<>();
        PriorityQueue<Integer> max_pq = new PriorityQueue<>(Comparator.reverseOrder());
        Set<Integer> sync = new HashSet<>(); // 동기화를 위한 set
        
        for (String operation : operations){
            parseOperation(operation, min_pq, max_pq, sync);
        }
        
        syncSet(min_pq, max_pq, sync);
        
        if (sync.isEmpty()){
            return new int[] {0,0};
        }
        int max = max_pq.peek();
        int min = min_pq.peek();
        
        return new int[] {max, min};
    }
    
    private void parseOperation (String operation, PriorityQueue<Integer> min_pq, PriorityQueue<Integer> max_pq, Set<Integer> sync){
        String[] args = operation.split(" ");
        String op = args[0];
        String sub_str = args[1];
        
        if (op.equals("I")){
            Integer sub = Integer.parseInt(sub_str);
            min_pq.offer(sub);
            max_pq.offer(sub);
            sync.add(sub);
        }
        
        if (op.equals("D")){
            Integer sub = Integer.parseInt(sub_str);
            
            if (sub < 0){
                syncSet(min_pq, max_pq, sync);
                sync.remove(min_pq.poll());
            }else{
                syncSet(min_pq, max_pq, sync);
                sync.remove(max_pq.poll());
            }
        }
    }
        
    private void syncSet(PriorityQueue<Integer> min_pq, PriorityQueue<Integer> max_pq, Set<Integer> sync){
        while (!min_pq.isEmpty() && !sync.contains(min_pq.peek())){
            min_pq.poll();
        }
        while (!max_pq.isEmpty() && !sync.contains(max_pq.peek())){
            max_pq.poll();
        }
    }
}
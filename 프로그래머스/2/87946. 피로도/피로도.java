
/*
- 소모피로도, 최소 필요 피로도
    - 가장 많이 탐색할 수 있는 경우의 수 찾기

- bfs로 접근하기
*/

import java.util.*;

class Solution {
    class State {
        private int k;
        private int cnt;
        private boolean[] visited;
        
        public State(int k, int cnt, boolean[] visited){
            this.k = k;
            this.cnt = cnt;
            this.visited = visited;
        }
        
        public int getK(){
            return k;
        }
        
        public int getCnt(){
            return cnt;
        }
        
        public boolean[] getVisited(){
            return visited;
        }
    
    }
    
    public int solution(int k, int[][] dungeons) {
        Queue<State> deque = new LinkedList<>();
        boolean[] visited = new boolean[dungeons.length];
    
        deque.add(new State(k, 0, visited));
        
        int answer = 0;
        
        while (!deque.isEmpty()){
            State st = deque.poll();
            answer = Math.max(answer, st.getCnt());
            for (int i = 0; i < dungeons.length; i++){
                boolean[] temp = st.getVisited().clone();
                if (temp[i] == false && st.getK() >= dungeons[i][0]){
                    temp[i] = true;
                    deque.add(new State(st.getK() - dungeons[i][1], st.getCnt() + 1, temp));
                }
            }
        }
        
        return answer;
    }
}
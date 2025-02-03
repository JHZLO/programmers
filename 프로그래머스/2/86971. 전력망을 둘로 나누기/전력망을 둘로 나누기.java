/*
<요구 조건>
- 전선들 중 하나를 끊어 네트워크 망을 2개로 분할
    - 두 전력망이 갖게 되는 송전탑의 개수를 "최대한 비슷하게"

<입력>
- n : 송전탑의 개수
- wires[][] : 전선 정보
    - [v1, v2] : v1번의 송전탑과 v2번의 송전탑이 연결되어 있음

<출력>
- return : 두 전력망이 가지고 있는 송전탑 개수의 차이 (절대값)

<문제 풀이>
- 양방향 그래프 구성
- 1번부터 시작해서 완전 탐색 (BFS)
    - 중간중간 계속 끊어내면서 배열에 넣어놓기
*/
import java.util.*;

class Solution {
    public class Node {
        private int num;
        private List<Node> edge; // 연결되어 있는 노드 추가
        private boolean visited;
        
        public Node (int num){
            this.num = num;
            this.edge = new LinkedList<>();
        }
        
        public void addNode (Node node){
            edge.add(node);
        }
    }
    
    public int solution(int n, int[][] wires) {
        List<Node> graph = new ArrayList<>();
        
        for (int i = 0; i < n; i++){
            Node node = new Node(i);
            graph.add(node);
        }
        
        // 양방향 그래프
        for (int[] conn: wires){
            Node node1 = graph.get(conn[0] - 1);
            Node node2 = graph.get(conn[1] - 1);
            
            node1.addNode(node2);
            node2.addNode(node1);
        }
        
        int min = 100;
        
        // 1~n번까지 해당 노드를 기준으로 끊어내며 완전 탐색
        for (int i = 0; i < n; i++){
            Node first = graph.get(i);
            for (Node second : first.edge){
                int cnt1 = bfs(first, second, graph, n);
                int cnt2 = bfs(second, first, graph, n);
                
                min = Math.min(min, Math.abs(cnt1 - cnt2));
            }
        }
        
        return min;
    }
    
    private int bfs(Node first, Node second, List<Node> graph, int n){
        
        // 방문 초기화
        for (int i = 0; i < n; i++){
            graph.get(i).visited = false;
        }
        
        // first node와 second node의 망 끊기
        first.visited = true;
        second.visited = true;
        
        Queue<Node> deque = new LinkedList<>();
        
        deque.offer(first);
        
        int cnt = 0;
        
        while (!deque.isEmpty()){
            Node target = deque.poll();
            
            for (Node child : target.edge){
                if (!child.visited){
                    deque.offer(child);
                    child.visited = true;
                    cnt ++;
                }
            }
        }
        
        return cnt;
    }
}
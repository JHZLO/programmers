/*
- 양방향 그래프가 주어졌을 때 네트워크의 개수 구하기
*/
import java.util.*;

class Solution {
    class Node {
        int i;
        boolean visited;
        List<Node> child;
        
        public Node(int i){
            this.i = i;
            this.child = new ArrayList<>();
        }
        
        private void addNode(Node node){
            child.add(node);
        }
    }
    
    public int solution(int n, int[][] computers) {
        List<Node> network = new ArrayList<>();
        Queue<Node> que = new LinkedList<>();
        
        for (int i = 0; i < n; i++){
            Node node = new Node(i);
            network.add(node);
        }
        
        for (int i = 0; i < n; i++){
            Node node = network.get(i);
            for (int j = 0; j < n; j++){
                if (i == j){
                    continue;
                }
                if (computers[i][j] == 1){
                    node.addNode(network.get(j));
                }
            }
        }
        
        int cnt = 0;
        for (Node node : network){
            if (node.visited == false){
                que.offer(node);
            }
            if (node.visited == true){
                continue;
            }
            while (!que.isEmpty()){
                Node com = que.poll();

                for (Node child : com.child){
                    if (!child.visited){
                        que.offer(child);
                        child.visited = true;
                    }
                }
            }
            cnt++;
        }
        
        return cnt;
    }
}
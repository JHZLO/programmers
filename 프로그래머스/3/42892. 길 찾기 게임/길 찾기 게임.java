/*
1. 트리 구성
- y값을 기준으로 내림차순
- x값을 비교하여 작으면 leftChild, 크면 rightChild
2. 순회
*/
import java.util.*;

class Solution {
    static int [][] answer; 
    static int cnt = 0;
    
    class Node{
        int x;
        int y;
        int v;
        Node leftChild;
        Node rightChild;
        
        public Node(int x, int y, int v){
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        List<Node> binaryTree = new ArrayList<>();
        
        for (int i = 0; i < nodeinfo.length; i++){
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            int v = i + 1;
            binaryTree.add(new Node(x, y, v));
        }
        
        binaryTree.sort((a, b) -> Integer.compare(b.y, a.y));
        
        Node root = binaryTree.get(0);
        
        // 트리 생성
        for (int i = 1; i < binaryTree.size(); i++){
            linkChild(root, binaryTree.get(i));
        }
        
        answer = new int[2][binaryTree.size()];
        pre(root);
        cnt = 0;
        post(root);
        
        return answer;
    }
    private void pre(Node node){ // 전위
        answer[0][cnt++] = node.v;
        if (node.leftChild != null){
            pre(node.leftChild);
        }
        if (node.rightChild != null){
            pre(node.rightChild);
        }
        
    }
    
    private void post(Node node){ // 후위
        if (node.leftChild != null){
            post(node.leftChild);
        }
        if (node.rightChild != null){
            post(node.rightChild);
        }
        answer[1][cnt++] = node.v;
    }
    
    private void linkChild(Node parent, Node child){
        if (parent.x > child.x){
            if (parent.leftChild == null){
                parent.leftChild = child;
            }else {
                linkChild(parent.leftChild, child);
            }
        }else{
            if (parent.rightChild == null){
                parent.rightChild = child;
            }else {
                linkChild(parent.rightChild, child);
            }
        }
    }
}
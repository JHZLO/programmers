/*
- 주어진 항공권을 모두 이용하여 여행경로를 짬
    - 방문할 수 있는 공항 경로를 배열에 담기
    - 여러 개인 경우 알파벳 순으로
- 항상 "ICN" 공항에서 출발함
*/
import java.util.*;

class Solution {
    List<String> result = new ArrayList<>();
    boolean[] visited;
    
    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, Comparator.comparing((String[] ticket) -> ticket[1])); // 도착지 기준 알파벳 순 정렬
        visited = new boolean[tickets.length];
        
        dfs("ICN", "ICN", tickets, 0);
        
        return result.get(0).split(" ");
    }
    
    private void dfs(String current, String path, String[][] tickets, int count) {
        if (count == tickets.length) {
            result.add(path);  // 모든 항공권을 사용한 경우 경로 저장
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(current)) {
                visited[i] = true;
                dfs(tickets[i][1], path + " " + tickets[i][1], tickets, count + 1);
                visited[i] = false;  // 백트래킹
            }
        }
    }
}
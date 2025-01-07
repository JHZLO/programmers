import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        Map<String, Integer> clothesMap = new HashMap<>();
        
        for (String[] cloth : clothes) {
            clothesMap.put(cloth[1], clothesMap.getOrDefault(cloth[1], 0) + 1);
        }
        
        for (int count : clothesMap.values()) {
            // 각 카테고리의 아이템 개수 + 선택하지 않는 경우(1)
            answer *= (count + 1);
        }
        
        // 모든 카테고리에서 아무것도 선택하지 않는 경우 제외
        answer -= 1;
        
        return answer;
    }
}
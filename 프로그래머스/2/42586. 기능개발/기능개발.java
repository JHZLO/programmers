import java.util.*;

/*
return : 각 배포마다 몇 개의 기능이 배포되는지

- 일단 각각 며칠이 걸리는지 배열로 만듦
- n번째보다 n+1번째가 작거나 같은 경우 같이 묶음
    - 크면 그다음 index에서 count

주의
- 배포는 하루에 한 번만 가능
- 진도 100프로 채워야함

*/

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        List<Integer> complete_days = new ArrayList<>();
        
        for (int i = 0; i < progresses.length; i++){
            complete_days.add((int) Math.ceil((double) (100 - progresses[i]) / speeds[i]));
        }
        
        int count = 1;
        int maxDay = complete_days.get(0);
        
        for (int i = 1; i < complete_days.size(); i++){
            if (complete_days.get(i) <= maxDay){
                count ++;
            }else{
                answer.add(count);
                count = 1;
                maxDay = complete_days.get(i);
            }
        }
        answer.add(count);
        
        return answer.stream().mapToInt(Integer :: intValue).toArray();
    }
}
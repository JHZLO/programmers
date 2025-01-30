/*
- 수포자 1, 2, 3의 문제 푸는 방식 패턴
    - 수포자 1 : 1,2,3,4,5 반복
    - 수포자 2 : 2,1,2,3,2,4,2,5 반복
    - 수포자 3 : 3,3,1,1,2,2,4,4,5,5 반복
- answers를 가장 많이 맞춘 사람? 
*/
import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        List<Integer> answer = new ArrayList<>();
        
        int[] math_1 = new int[]{1,2,3,4,5};
        int[] math_2 = new int[]{2,1,2,3,2,4,2,5};
        int[] math_3 = new int[]{3,3,1,1,2,2,4,4,5,5};
        int[][] math = {math_1, math_2, math_3};
        int[] counts = new int[]{0,0,0};
        
        int max_count = 0;
        
        for (int i = 0; i < 3; i ++){
            int count = 0;
            for (int j = 0; j < answers.length; j++){
                if (math[i][j % math[i].length] == answers[j]){
                    count ++;
                    counts[i] = count;
                }
            }
            max_count = Math.max(max_count, count);
        }
        
        for (int k = 0; k < 3; k++){
            if (counts[k] == max_count){
                answer.add(k+1);
            }
        }
        
        return answer.stream().mapToInt(Integer :: intValue).toArray();
    }
}
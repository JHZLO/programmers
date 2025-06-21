/*
- 숫자가 주어지면 숫자들의 조합으로 부호를 붙여서 target값을 만들 수 있는 경우의 수?
- root가 맨 첫번째 수의 +, - 부호 붙인 숫자


처음이 + 일때, 
*/
import java.util.*;

class Solution {
    public static int answer = 0;
    public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        
        return answer;
    }
    
    public void dfs (int depth, int sum, int[] numbers, int target){
        if (depth == numbers.length){
            if (sum == target){
                answer += 1;
            }
            return;
        }
        
        dfs(depth + 1, sum + numbers[depth], numbers, target);
        dfs(depth + 1, sum - numbers[depth], numbers, target);
    }
}
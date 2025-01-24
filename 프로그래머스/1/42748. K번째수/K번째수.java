/*
- commands 루프 돌면서 접근하기
    - 1번 째 인덱스 : 첫번째 순서
    - 2번 째 인덱스 : 마지막 순서
    - 3번 째 인덱스 : 정렬 후 해당 인덱스 번호
- split -> 정렬 -> get
*/
import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] results = new int[commands.length];
        
        for (int i = 0; i < commands.length; i++) {
            int start = commands[i][0] - 1;
            int end = commands[i][1];
            int k = commands[i][2] - 1;
            
            int[] subArray = Arrays.copyOfRange(array, start, end);
            
            Arrays.sort(subArray);

            results[i] = subArray[k];
        }
        
        return results;
    }
}
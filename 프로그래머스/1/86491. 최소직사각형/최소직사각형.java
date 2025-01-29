/*
- 주어진 명함의 가로 세로의 길이를 고려하여 모든 명함을 수납할 수 있는 지갑 크기 구하기
    - 이때 가로 세로를 서로 바꿔서 넣는 것도 허용한다.
    - 내부에 있는 배열을 정렬 후 max x max값으로 크기 설정하자
*/
import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int maxWidth = 0;
        int maxLength = 0;
        
        for (int i = 0; i < sizes.length; i++){
            Arrays.sort(sizes[i]);
            for (int j = 0; j < 2; j++){
                maxWidth = Math.max(maxWidth, sizes[i][0]);
                maxLength = Math.max(maxLength, sizes[i][1]);
            }
        }
       
        return maxWidth * maxLength;
    }  
}
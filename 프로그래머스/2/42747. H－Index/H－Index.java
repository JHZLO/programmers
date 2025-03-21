/*
논문 n편 중, h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면
h의 최댓값이 이 과학자의 H-Index
*/
import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int h_index = citations.length;
        
        for (int i = 0 ; i < citations.length; i++){
            if (citations[i] >= h_index){
                break;
            }else {
                h_index -= 1;
            }
        }
        return h_index;
    }
}
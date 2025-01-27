/*
- 배열 오름차순 정렬
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
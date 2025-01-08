import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        List<Integer> ret = new ArrayList<>();
        
        for (int i = 0; i < arr.length; i++){
            if (i == 0){
                ret.add(arr[i]);
            }else if(arr[i-1] != arr[i]){
                ret.add(arr[i]);
            }
        }

        return ret.stream().mapToInt(Integer::intValue).toArray();
    }
}
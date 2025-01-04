import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int[] arr = new int[200001];
        
        int trial = nums.length / 2;
        
        for(int i=0; i<nums.length; i++){
            arr[nums[i]] ++;
        }
        
        int count = 0;
        for(int index : arr){
            if(index != 0){
                count ++;
            }
        }
        
        if(count > trial){
            return trial;
        }
        return count;
    }
}
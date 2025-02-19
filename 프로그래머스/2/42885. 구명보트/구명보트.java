/*
- 구명보트는 작아서 한 번에 2명씩 밖에 탈 수 없다.
*/
import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        boolean[] check = new boolean[people.length];
        Arrays.sort(people);
        int max_index = people.length - 1;
        int min_index = 0;
        
        int total_weight = 0;
        int count = 0;
        while (max_index >= min_index){
            if (people[min_index] + people[max_index] <= limit){
                min_index ++;
            }
            max_index --;
            count += 1;
        }
        
        return count;
    }
}
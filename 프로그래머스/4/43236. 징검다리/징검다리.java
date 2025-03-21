/*
0, 2, 11, 14, 17, 21, 25
 2   9   3   3   4   4

이분탐색
right = distance
left = 0
mid = (right + left) / 2
mid보다 작은 거리이면 rock을 제거하고 큰 거리면 유지
그리고 나서 rock의 제거된 개수가 만약 n보다 크다면 mid를 낮춰서 재설정
작다면 mid를 크게 해서 재설정
=> 반복하면서 최솟값 중에 가장 큰 값 찾기
*/
import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        
        int left = 0;
        int right = distance;
        
        while (left <= right){
            int mid = (left + right) / 2;
            if (getCnt(rocks, distance, mid) <= n){
                answer = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        
        return answer;
    }
    private int getCnt(int[] rocks, int distance, int mid){
        int before = 0;
        int remove = 0;
        int end = distance;
        
        for (int i = 0; i < rocks.length; i++){
            if (rocks[i] - before < mid){
                remove ++;
                continue;
            }
            before = rocks[i];
            
        }
        if (end - before < mid){
            remove ++;
        }
        
        return remove;
    }
}

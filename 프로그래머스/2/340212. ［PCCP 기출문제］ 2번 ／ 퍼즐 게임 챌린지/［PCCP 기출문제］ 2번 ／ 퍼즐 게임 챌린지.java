/*
- diff ≤ level이면 퍼즐을 틀리지 않고 time_cur만큼의 시간을 사용하여 해결합니다.
- diff > level이면, 퍼즐을 총 diff - level번 틀립니다.
퍼즐을 틀릴 때마다, time_cur만큼의 시간을 사용하며, 
추가로 time_prev만큼의 시간을 사용해 이전 퍼즐을 다시 풀고 와야함
=> diff-level만큼 반복후 time_cur만큼의 시간 사용

완탐은 너무 오래걸리지 않나..?
*/

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = binarySearch(diffs, times, limit);
        
        return answer;
    }
    private int binarySearch(int[] diffs, int[] times, long limit){
        int max = 1, min = 100000;
        while (min >= max){
            int level = (min + max) / 2;
            long cur = 0;
            for (int i = 0; i < diffs.length; i++){
                if (diffs[i] <= level){
                    cur += times[i];
                }else{
                    cur += (long)(times[i-1] + times[i]) * (long)(diffs[i] - level);
                    cur += times[i];
                }
            }
            if (cur > limit){
                max = level + 1;
            }else{
                min = level - 1;
            }   
        }
        return max;
    }
}
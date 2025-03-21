/*
우선 순위 큐에다가 끝나는 시간을 집어넣으면 되지 않을까?
7   14  21  28
10  20  30

아니면 무조건 배수로 끝난다는 특징이 있으니까..
근데 너무 광범위함


*/

class Solution {
    public long solution(int n, int[] times) {
        long end = Long.MAX_VALUE / 100;
        long start = 0;
        
        while (start < end){
            long mid = (start + end) / 2;
            long cnt = check(times, mid);
            
            if (cnt >= n){
                end = mid;
            }else{
                start = mid + 1;
            }
        }
        
        return start;
    }
    private long check(int[] times, long total_time){
        long sum = 0;
        
        for (int i = 0; i < times.length; i++){
            sum += total_time / times[i];
        }
        
        return sum;
    }
}
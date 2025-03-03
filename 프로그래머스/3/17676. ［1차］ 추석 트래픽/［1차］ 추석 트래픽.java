/*
<요구조건>
- 요청완료시간 S
- 처리시간 T
    - 최대 소수점 셋째 자리
    - 처리시간은 시작시간과 끝시간을 포함
    - 0.001 <= T <= 3.000
        - 타임 아웃
        
lines -> S를 기준으로 오름차순 정렬

<출력>
lines 배열에 대해 초당 최대 처리량 리턴

<풀이>
최대한 겹치는 부분을 구하는 게 목적임
0.001 단위로 세면서 겹치는 최댓값을 구하는건 너무 빡셈

하나씩 for문 돌리면서 1초동안에 속하는 값이 있으면 cnt++
최댓값 cnt 구하기
*/
import java.util.*;

class Solution {
    public int solution(String[] lines) {
        int max_cnt = 0;
        List<Integer> start_time_lines = new ArrayList<>();
        List<Integer> end_time_lines = new ArrayList<>();
        int second = 1000;
        
        for (String line: lines){
            int end_time = parseTime(line);
            int duration = parseDuration(line);
            int start_time = end_time - duration + 1;
            start_time_lines.add(start_time);
            end_time_lines.add(end_time);
        }
        
        int cnt;
        for (int i = 0; i < lines.length; i ++){
            cnt = 1;
            int end = end_time_lines.get(i);
            for (int j = i + 1; j < lines.length; j++){
                int target = start_time_lines.get(j);
                if (end  > target - second){
                    cnt ++;
                }
            }
            max_cnt = Math.max(max_cnt, cnt);
        }
        
        return max_cnt;
    }
    
    private int parseTime(String line){ // 초단위로 변환
        int end_hour = Integer.parseInt(line.substring(11,13)) * 3600 * 1000;
        int end_minute = Integer.parseInt(line.substring(14, 16)) * 60 * 1000;
        int end_sec = Integer.parseInt(line.substring(17,19)) * 1000;
        int end_mil_sec = Integer.parseInt(line.substring(20,23));
        
        int end_time = end_hour + end_minute + end_sec + end_mil_sec;
        
        return end_time;
    }
    
    private int parseDuration(String line){
        int duration = (int)(Double.parseDouble(line.substring(24, line.length() - 1)) * 1000);
        
        return duration;
    }
}
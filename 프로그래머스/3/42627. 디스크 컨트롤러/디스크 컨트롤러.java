/*
<문제 요구 조건>
- 작업 대기 큐에 작업을 대기 시킴
    - PriorityQueue 활용
    - 작업 시간이 짧을수록 우선순위가 높음
    - 큐에 여러 개의 작업이 있을 때 '우선 순위가 높은 작업을 먼저 수행'
- 작업을 한번 시작하면 작업을 마칠 떄까지 그 작업만 수행
- 반환 시간 = 작업 종료 시각 - 요청 시각

<입력>
- jobs 2차원 배열
    - jobs = [[a,b],[a,b],[a,b]]
        - a : 요청 시각
        - b : 작업 소요 시간
    - jobs의 길이 : 요청된 총 작업 수
    
<출력>
- 모든 요청 작업의 반환 시간의 평균
*/

import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        final int START_TIME_INDEX = 0;
        final int WORK_TIME_INDEX = 1;
        
        Arrays.sort(jobs, (o1, o2) -> o1[START_TIME_INDEX] - o2[START_TIME_INDEX]);
        
        Queue<Integer[]> queue = new PriorityQueue<>((o1, o2) -> (o1[WORK_TIME_INDEX] - o2[WORK_TIME_INDEX]));
        
        int totalTime = 0; // 총 걸리는 시간
        int work_index = 0; // 현재 처리 중인 작업의 인덱스
        int returnTime = 0; // 반환 시간의 총 합
        int completedJobs = 0; // 완료된 작업 수
        
        while(completedJobs < jobs.length){
            while (work_index < jobs.length && jobs[work_index][START_TIME_INDEX] <= totalTime) {
                queue.add(new Integer[]{jobs[work_index][START_TIME_INDEX], jobs[work_index][WORK_TIME_INDEX]});
                work_index++;
            }

            if (queue.isEmpty()) {
                totalTime = jobs[work_index][START_TIME_INDEX];
                continue;
            }

            Integer[] times = queue.remove();
            totalTime += times[WORK_TIME_INDEX];
            returnTime += totalTime - times[START_TIME_INDEX];
            completedJobs++;
        }
        
        return returnTime / jobs.length;
    }
}
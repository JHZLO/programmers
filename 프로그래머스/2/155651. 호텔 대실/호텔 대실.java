import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int[][] timeDiff = new int[book_time.length][2];

        for (int i = 0; i < book_time.length; i++) {
            int start = convertToMinutes(book_time[i][0]);
            int end = convertToMinutes(book_time[i][1]) + 10; // 청소 시간 추가
            timeDiff[i] = new int[]{start, end};
        }

        // 시작 시간 기준으로 정렬
        Arrays.sort(timeDiff, Comparator.comparingInt(v -> v[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] time : timeDiff) {
            int start = time[0];
            int end = time[1];

            // 가장 빨리 끝나는 방의 종료 시간이 현재 시작 시간보다 작거나 같으면 재사용 가능
            if (!pq.isEmpty() && pq.peek() <= start) {
                pq.poll();
            }

            pq.offer(end); 
        }

        return pq.size();
    }

    private int convertToMinutes(String time) {
        String[] args = time.split(":");
        return Integer.parseInt(args[0]) * 60 + Integer.parseInt(args[1]);
    }
}

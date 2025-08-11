import java.util.*;

class Solution {

    private static final int SECONDS_IN_HOUR = 60 * 60;
    private static final int SECONDS_IN_MINUTE = 60;

    public String solution(String play_time, String adv_time, String[] logs) {
        // 전체 재생 시간, 광고 시간(초 단위 변환)
        int playTime = timeToSec(play_time);
        int advTime = timeToSec(adv_time);

        // 광고 길이 = 전체 길이 → 0초에 시작
        if (playTime == advTime) return secToTime(0);

        // 시청자 수 변화량 저장 (Imos 마킹)
        long[] viewers = new long[playTime + 2]; // end+1 접근 대비

        for (String log : logs) {
            String[] parts = log.split("-");
            int start = timeToSec(parts[0]);
            int end = timeToSec(parts[1]);
            viewers[start] += 1;
            viewers[end] -= 1;
        }

        // 1차 누적합: t초 시청자 수
        for (int i = 1; i <= playTime; i++) {
            viewers[i] += viewers[i - 1];
        }

        // 2차 누적합: 0~t초까지 누적 시청 시간
        for (int i = 1; i <= playTime; i++) {
            viewers[i] += viewers[i - 1];
        }

        // 초기 광고 구간(0~advTime)의 누적 시청 시간
        long maxViewTime = viewers[advTime];
        int bestStart = 0;

        // 슬라이딩 윈도우로 최적 광고 시작 시각 탐색
        for (int start = 1; start + advTime <= playTime; start++) {
            int end = start + advTime - 1;
            long currentViewTime = viewers[end] - viewers[start - 1];
            if (currentViewTime > maxViewTime) {
                maxViewTime = currentViewTime;
                bestStart = start;
            }
        }

        return secToTime(bestStart);
    }

    /** HH:MM:SS → 초 단위 변환 */
    private int timeToSec(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int min = Integer.parseInt(parts[1]);
        int sec = Integer.parseInt(parts[2]);
        return hour * SECONDS_IN_HOUR + min * SECONDS_IN_MINUTE + sec;
    }

    /** 초 단위 → HH:MM:SS 변환 */
    private String secToTime(int totalSec) {
        int hour = totalSec / SECONDS_IN_HOUR;
        totalSec %= SECONDS_IN_HOUR;
        int min = totalSec / SECONDS_IN_MINUTE;
        int sec = totalSec % SECONDS_IN_MINUTE;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
}

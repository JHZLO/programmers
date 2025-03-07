/*
각 포인트에서 이동하는 최단 거리. => BFS
한층씩 각자 최단 거리로 이동
BFS로 탐색하면서 좌표가 겹치는 경우 count

각자 queue로 담을것인가?

각자 queue로 움직이되, 초 단위로 이동하는 경로를 남겨야 같은 초에 두 개 이상인 게 충돌이라 판단

MAP<Integer, int[]> ~> contains이면 충돌++
*/
import java.util.*;

class Solution {
    private static int max_x;
    private static int max_y;
    
    private static int crash = 0;
    
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    
    private static Map<Integer, Map<String, Integer>> nav = new HashMap<>(); // 시간, <pos, 횟수>
    
    public int solution(int[][] points, int[][] routes) {

        for (int[] route : routes) {
            int time = 0;
            for (int i = 0; i < route.length - 1; i++) {
                int[] from = {points[route[i] - 1][0] - 1, points[route[i] - 1][1] - 1};
                int[] to = {points[route[i+1] - 1][0] - 1, points[route[i+1] - 1][1] - 1};

                time = moveRobot(from, to, time);
            }
        }
        
        for (Map<String, Integer> positions : nav.values()) {
            for (int count : positions.values()) {
                if (count > 1) {
                    crash ++;
                }
            }
        }

        return crash;
    }
    
    private int moveRobot(int[] from, int[] to, int time) {
        int x1 = from[0];
        int y1 = from[1];
        int x2 = to[0];
        int y2 = to[1];
        
        if (time == 0) {
          String pos = x1 + "," + y1;
          nav.putIfAbsent(time, new HashMap<>());
          Map<String, Integer> timestamp = nav.get(time);
          timestamp.put(pos, timestamp.getOrDefault(pos, 0) + 1);
        }
        
        while (x1 != x2 || y1 != y2){
            if (x1 < x2){
                x1 ++;
            }else if (x1 > x2){
                x1 --;
            }else if (y1 < y2){
                y1 ++;
            }else if (y1 > y2){
                y1 --;
            }
            
            time ++;
            
            String cur = x1 + "," + y1;
            
            nav.putIfAbsent(time, new HashMap<>());
            Map<String, Integer> timestamp = nav.get(time);
            timestamp.put(cur, timestamp.getOrDefault(cur, 0) + 1);
            time ++;
        }
        
        return time;
    }
}


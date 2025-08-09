/*
- 두 사람 사이에 더 많은 선물은 준 사람이 다음달에 선물을 하나 받는다.
- 주고받은 기록 x, 같으면 -> 선물 지수가 큰 사람이 작은 사람에게 하나 받음
    - 선물 지수 : (이번 달까지) 선물 준 수 - 자신이 받은 수
    - 선물 지수조차 같으면 다음달에 선물을 주고받지 않음
=> 선물을 가장 많이 받을 친구?
*/
import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int N = friends.length;
        int[][] arr = new int[N][N];
        Map<String, Integer> friendsMap = new HashMap<>();
        
        for(int i = 0; i < N; i++) {
            friendsMap.put(friends[i], i);
        }
        
        for (String gift: gifts){
            String[] names = gift.split(" ");
            String sender = names[0];
            String receiver = names[1];
            
            arr[friendsMap.get(sender)][friendsMap.get(receiver)]++;
        }
        
        int[] points = new int[N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                points[i] += arr[i][j];
                points[j] -= arr[i][j];
            }
        }
        
        int[] res = new int[N];
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                if(arr[i][j] > arr[j][i]) res[i]++;
                else if(arr[i][j] < arr[j][i]) res[j]++;
                else {
                    if(points[i] > points[j]) res[i]++;
                    else if(points[i] < points[j]) res[j]++;
                }       
            }
        }

        int max = 0;

        for(int i = 0; i < N; i++) if(max < res[i]) max = res[i];

        return max;
    }
}
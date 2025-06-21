/*
카메라의 진입/진출 지점에 카메라가 설치 되어있어도 카메라를 만난 것으로 간주
>> 모든 차량이 한 번은 단속용 카메라를 만나도록 하기 위해 최소 몇 대의 카메라 설치?

문제풀이
- 각 차의 시작 지점 순으로 정렬
- routes.length => N
- 각 차의 끝나는 지점과 다음 순서들의 차량의 시작 지점과 비교
    - 끝나는 지점 > 시작 지점 => N -1 (남은 차량만큼 반복)
    - 끝나는 지점 < 시작 지점 => break -> 해당 차량부터 다시 위의 로직 반복
*/
import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int N = routes.length;
        int answer = N;
        
        Arrays.sort(routes, (a,b) -> Integer.compare(a[0], b[0]));
        int target = routes[0][1];
        
        for (int i = 1; i < N; i ++){
            if (target >= routes[i][0]){
                if (target >= routes[i][1]){
                    target = routes[i][1];
                }
                answer -= 1;
            }else{
                target = routes[i][1];
            }
        }
        
        return answer;
    }
}
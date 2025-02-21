/*
<요구 조건>
- 모든 차량이 단속용 카메라를 한 번은 만나도록 카메라를 설치
- 차량의 진입/진출 지점에 카메라가 설치되어 있어도 카메라를 만난것으로 간주
- 차량의 대수는 1대 이상 10,000대 이하
- 차량의 진입 지점, 진출 지점은 -30,000 이상 30,000 이하

<입력>
routes[i][0] : i번째 차량이 고속도로에 진입한 지점,
routes[i][1] : i번째 차량이 고속도로에서 나간 지점

<출력>
최소의 몇 대 카메라 설치?

<문제 풀이>
차량의 진입 지점을 기준으로 오름차순 정렬
[-20, -15], [-18, -13], [-14, -5], [-5, -3]
0번쨰 인덱스부터 진출 지점에 카메라 설치,
- 카메라를 통과하는 route는 패스
- 통과하지 못하면 해당 route의 진출 지점에 또 카메라 설치
*/
import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(route -> route[1]));
        
        int pos = routes[0][1];
        int answer = 1;
        
        for (int i = 0; i < routes.length; i ++){
            if (routes[i][0] <= pos && pos <= routes[i][1]){
                continue;
            }else{
                answer ++;
                pos = routes[i][1];
            }
        }
        
        return answer;
    }
}
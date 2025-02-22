/*
숫자를 i 개 사용했을때 만들어지는 모든 수들을 하나의 통에 담는다

1번통 = 숫자 1개로 만들수 있는 값 = N (자기 자신뿐)
2번통 = 숫자 2개로 만들수 있는 값 = 1번통 (+, -, *, /) 1번통
3번통 = 숫자 3개로 만들수 있는 값 = 1번통 (+, -, *, /) 2번통, 2번통 (+, -, *, /) 1번통
4번통 = 1,3 3,1 2,2

<가능한 연산>
a+b
a-b
a*b
a/b (a!=0 && b!=0)
N을 i번째 통에서 i만큼 String으로 이어붙이기
(위의 연산을 반대로도 수행)
*/
import java.util.*;

class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> dp = new ArrayList<>();
        
        for (int i = 0; i <= 8; i++){ // 최솟값이 8보다 크면 -1 반환
            dp.add(new HashSet<>());
        }
        
        if (N == number){
            return 1;
        }
        dp.get(1).add(N); // 1번 통에는 N 하나만 들어있음
        
        for (int i = 2; i <= 8; i++){
            Set<Integer> total = dp.get(i); // i번째 통 hashSet
            for (int j = 1; j < i; j++){ //  2번째 통은 1, 1로 만들 수 있음
                Set<Integer> a = dp.get(j);
                Set<Integer> b = dp.get(i-j);
                
                for (int x: a){
                    for (int y: b){
                        total.add(x+y);
                        total.add(x-y);
                        total.add(x*y);
                        if (x != 0 && y != 0){
                            total.add(x/y);
                        }
                    }
                }
                
                total.add(Integer.parseInt(String.valueOf(N).repeat(i)));
            }
            
            if (total.contains(number)){
                return i;
            }
        }
        
        return -1;
    }
}
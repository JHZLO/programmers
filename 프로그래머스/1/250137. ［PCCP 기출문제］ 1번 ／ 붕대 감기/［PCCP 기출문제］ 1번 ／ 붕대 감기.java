/*
t초 동안 붕대를 감으면서 1초마다 x만큼의 체력을 회복함
t초 연속으로 성공 -> y만큼 체력 추가 획득
최대 체력보다 커질 수 없음
공격을 당하면 붕대 감기 취소 + 공격을 당하는 순간에는 회복 못함
(이렇게 해서 취소되면 연속 성공시간이 0으로 초기화)

몬스터의 공격은 정해진 피해량만큼 체력이 줄어듦 
0 이하면 죽음

캐릭터가 끝까지 생존할 수 있을까? => 남은 체력 return
*/
import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int max_time = attacks[attacks.length - 1][0];
        int successive_time = bandage[0];
        int recovery_unit = bandage[1];
        int recovery_bonus = bandage[2];
        int max_health = health;
        
        Map<Integer, Integer> attackMap = new HashMap<>();
        for (int[] attack: attacks){
            attackMap.put(attack[0], attack[1]);
        }
        
        int cnt = 0;
        for (int i = 1; i <= max_time; i++){
            cnt ++;
            if (attackMap.containsKey(i)){
                health -= attackMap.get(i);
                cnt = 0;
            }else{
                if (cnt < successive_time){
                    health += recovery_unit;
                }else{
                    health += recovery_bonus;
                    health += recovery_unit;
                    cnt = 0;
                }
            }
            if (health >= max_health){
                health = max_health;
            }
            if (health <= 0){
                return -1;
            }
        }
        
        return health;
    }
}
/*
case 1)
- 만들 수 있는 경우의 수를 배열에 넣어서 내림차순으로 정렬 후 가장 큰 수 반환
=> 시간이 너무 오래걸릴 것 같음

case 2)
- 맨 앞의 숫자만 뽑아내서 비교하여 정렬
- 맨 앞의 숫자가 같은 경우 비교
    - ex 3, 34가 있는경우 3을 33으로 치환 후 비교하여 더 큰 것을 앞으로
- 정렬 후 맨 앞 숫자부터 조합해서 만들기
*/

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String solution(int[] numbers) {
        Integer[] nums = Arrays.stream(numbers).boxed().toArray(Integer[]::new);
        
        Arrays.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2){
                String s1 = o1.toString();
                String s2 = o2.toString();
                
                return (s2 + s1).compareTo(s1 + s2);
            }
        });
        
        StringBuilder answer = new StringBuilder();
        for (Integer num : nums){
            answer.append(num);
        }
        
        if (answer.charAt(0) == '0'){
            return "0";
        }
        
        return answer.toString();
    }
}
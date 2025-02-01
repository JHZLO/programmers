/*
- 중간에만 껴있는 노랜색 카펫
- 갈색과 노란색의 격자 개수가 주어졌을 때 카펫 전체의 크기 구하기

<특징>
- [4,3] => 4*3 = 10 +2
이러한 특징을 이용하여 brown과 yellow를 더한 값을 두 개의 정수의 곱셈으로 나타낼 수 있음.
    - 두 개의 정수 중 (가로의 길이 >= 세로의 길이)를 만족하는 case에서 완전 탐색
    - 조건 :  (width - 2) * (height - 2) = yellow
*/
import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int area = brown + yellow; // 면적
        List<Integer> factors = new ArrayList<>(); // area의 약수
        int[] answer = {0,0};
        
        for (int i = 1; i <= area; i++){
            if (area % i == 0){
                int height = i;
                int width = area / height;
                if (width < height){
                    break;
                }
                if ((width - 2) * (height - 2) == yellow){
                    answer[0] = width;
                    answer[1] = height;
                }
            }
        }
        
        return answer;
    }
}